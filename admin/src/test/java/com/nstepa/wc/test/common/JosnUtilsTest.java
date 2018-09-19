package com.nstepa.wc.test.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.nstepa.wc.admin.Application;
import com.nstepa.wc.admin.wx.WxSessionBean;
import com.nstepa.wc.commons.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@ActiveProfiles({"dev"})
public class JosnUtilsTest {
	public Logger logger = LoggerFactory.getLogger(JosnUtilsTest.class);

	@Test
	public void getDistanceTest() {
		String body = "{\"openid\":\"21212\",\"session_key\":\"wewewe\",\"errcode\":40125,\"errmsg\":\"invalid " +
				"appsecret, view more at http:\\/\\/t.cn\\/RAEkdVq, hints: [ req_id: dwp6ha09822028 ]\"}";
		WxSessionBean sessionBean = JsonUtils.toBean(body, WxSessionBean.class);
		logger.info(sessionBean.toString());
	}

}
