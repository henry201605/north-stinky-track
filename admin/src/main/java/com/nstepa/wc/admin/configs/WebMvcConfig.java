package com.nstepa.wc.admin.configs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nstepa.wc.admin.services.UserService;
import com.nstepa.wc.beans.User;
import com.nstepa.wc.commons.pojos.Response;
import com.nstepa.wc.commons.utils.GsonUtils;
import com.nstepa.wc.redis.utils.RedisUtils;
import com.nstepa.wc.springboot.support.Requests;
import com.nstepa.wc.springboot.support.ResponseCode;
import com.nstepa.wc.springboot.support.annotations.LoginRequired;
import com.nstepa.wc.springboot.support.controller.BaseController;
import com.nstepa.wc.springboot.support.exceptions.NepaException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter implements EnvironmentAware {

    protected Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    private static final String DISPLAY_ERROR = "服务器开了个小差";

    private Environment env;

    @Autowired
    private  UserService  userService;
    @Resource
    private RedisUtils redisUtils;
    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addCheckLoginInterceptor(registry);
    }

    /**
     * 添加
     *
     * @param registry
     */
    private void addCheckLoginInterceptor(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (handler instanceof HandlerMethod) {
                    HandlerMethod mHandler = (HandlerMethod) handler;
                    //获取传入的3rd_session
//                    String skey = request.getParameter("3rd_session");
                    String skey = "6d01bc0b-00b5-4b8a-9683-baaa10e25234";
                    User user = tryToLoadAccount(mHandler, request, skey);

                    //如果要求做登录校验，检查信息是否正确
                    if (mHandler.hasMethodAnnotation(LoginRequired.class) && user == null) {
                        if (logger.isDebugEnabled())
                            logger.debug("[addCheckLoginInterceptor] Login required function without login message, skey -> {}, url -> {}",
                                    skey, request.getRequestURI());
                        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                        Response resp = BaseController.statusResponse(ResponseCode.NOT_LOGIN);
                        response.getOutputStream().write(GsonUtils.format(resp).getBytes());
                        return false;
                    }
                }
                return true;    //always true
            }
        });

    }

    /**
     * 根据skey获取用户信息
     * @param mHandler
     * @param request
     * @param skey
     * @return
     * @throws IllegalAccessException
     * @throws UnsupportedEncodingException
     */
    private User tryToLoadAccount(HandlerMethod mHandler, HttpServletRequest request, String skey) throws IllegalAccessException, UnsupportedEncodingException {

        if (logger.isDebugEnabled())
            logger.debug("[tryToLoadAccount([mHandler, request, skey])] Validate openId -> {}", skey);
        User user = null;
        //判断redis中是否存在传入的 3rd_session
        if(StringUtils.isNotBlank(skey) && redisUtils.hasKey(skey)){
            JSONObject sessionObj = JSON.parseObject((String) redisUtils.get(skey));
            String openId = (String) sessionObj.get("openId");
            if (StringUtils.isNotEmpty(openId)) {
                user = userService.selectOne(new EntityWrapper<User>().eq("user_key", openId));
                request.setAttribute(Requests.CURRENT_USER, user);
            }
        }
        return user;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH").allowCredentials(true).maxAge(3600);
    }

}