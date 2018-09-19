package com.nstepa.wc.admin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nstepa.wc.admin.services.UserService;
import com.nstepa.wc.admin.wx.WxSessionBean;
import com.nstepa.wc.beans.User;
import com.nstepa.wc.commons.pojos.Response;
import com.nstepa.wc.commons.utils.JsonUtils;
import com.nstepa.wc.redis.utils.RedisUtils;
import com.nstepa.wc.springboot.support.ResponseCode;
import com.nstepa.wc.springboot.support.controller.BaseController;
import com.nstepa.wc.springboot.support.exceptions.NepaException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Api(value = "login", description = "小程序登录认证")
@RestController
@RequestMapping(value = "login")
public class LoginController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Value("${weixin.config.appId}")
	private String appId;

	@Value("${weixin.config.secret}")
	private String secret;

	@Value("${weixin.code2session.url}")
	private String code2sessionUrl;

	@Autowired
	private RestTemplate restTemplate;
	@Resource
	private RedisUtils redisUtils;
	@Autowired
	private UserService userService;
	// 返回类型ResultDTO<String>
	private final ParameterizedTypeReference<String> responseType =
			new ParameterizedTypeReference<String>() {
			};
	//获取凭证校检接口
	@ApiOperation(value="系统登录验证", notes="验证用户登录信息")
	@PostMapping(value = "")
	public Response<Map<String,String>> doLogin(
			@ApiParam(value = "用户名",required = true) @NotBlank(message = "code不能为空") @RequestParam(required = false) String code,
			@ApiParam(value = "用户名",required = true) @NotBlank(message = "rawData不能为空") @RequestParam(required = false) String rawData){

		logger.info("用户非敏感信息"+rawData);
		JSONObject rawDataJson = JSON.parseObject(rawData);

		WxSessionBean sessionBean = getSessionKeyOrOpenId(code);
		logger.info("请求获取的SessionAndopenId="+sessionBean);

		String openId = sessionBean.getOpenid();
		String sessionKey = sessionBean.getSession_key();
		logger.info("openId="+openId+",session_key="+sessionKey);

		User user = userService.selectOne(new EntityWrapper<User>().eq("user_key", openId));
		//uuid生成唯一key
		String skey = UUID.randomUUID().toString();
		if(user==null){
			//存入数据库
			String nickName = rawDataJson.getString("nickName");
//			String avatarUrl = rawDataJson.getString("avatarUrl");//用户头像
//			String gender  = rawDataJson.getString("gender");//用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
			String city = rawDataJson.getString("city");
			String country = rawDataJson.getString("country");
			String province = rawDataJson.getString("province");

			user = new User();
			user.setUserKey(openId);
			user.setCreateTime(new Date());
			user.setAddress(country+" "+province+" "+city);
			user.setUserName(nickName);

			userService.insert(user);
		}else {
			//已存在
			logger.info("用户openId已存在,不需要插入");
		}
		//根据openId查询skey是否存在
		String skey_redis = (String) redisUtils.get(openId);
		if(StringUtils.isNotBlank(skey_redis)){
			//存在则删除skey ，重新生成skey 将skey返回
			redisUtils.del(skey_redis);
		}
		//  缓存一份新的
		JSONObject sessionObj = new JSONObject();
		sessionObj.put("openId",openId);
//		sessionObj.put("sessionKey",sessionKey);
		sessionObj.put("sessionKey","sessionKey");
		redisUtils.set(skey,sessionObj.toJSONString());
		redisUtils.set(openId,skey);

		Map<String,String> sessionMap = new HashMap<>();
		//把新的sessionKey和oppenid返回给小程序
		sessionMap.put("3rd_session",skey);
		return success(sessionMap);
	}

	/**
	 * 根据code获取session_key和openId
	 * @param code
	 * @return
	 */
	public WxSessionBean getSessionKeyOrOpenId(String code){
		ResponseEntity<String> resultEntity;
		try {
			resultEntity = this.restTemplate.exchange(
					this.code2sessionUrl,
					HttpMethod.GET,
					null,
					this.responseType,
					appId, secret, code);
		} catch (final RestClientException e) {
			logger.error(ResponseCode.FAIL_REQUEST_WX_SERVER.getMsg(), e);
			throw new NepaException(ResponseCode.FAIL_REQUEST_WX_SERVER);
		}
		WxSessionBean sessionBean = JsonUtils.toBean(resultEntity.getBody(), WxSessionBean.class);
		if (sessionBean != null && sessionBean.getErrcode() != null) {
			logger.error(ResponseCode.ERROR_RESPONSE_WX_SERVER.getMsg(), sessionBean.getErrcode());
			throw new NepaException(sessionBean.getErrcode(),sessionBean.getErrmsg());
		}
		return sessionBean;
	}
}

