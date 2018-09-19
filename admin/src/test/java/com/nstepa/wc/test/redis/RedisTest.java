package com.nstepa.wc.test.redis;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.nstepa.wc.admin.Application;
import com.nstepa.wc.redis.utils.RedisUtils;
import com.nstepa.wc.test.common.JosnUtilsTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@ActiveProfiles({"henry"})
public class RedisTest {
    public Logger logger = LoggerFactory.getLogger(JosnUtilsTest.class);

    @Resource
    private RedisUtils redisUtils;

    @Test
    public void testadd(){
        logger.info("hhe");
        redisUtils.set("key2","value2");
        String key2 = (String) redisUtils.get("key2");
        logger.info(key2);
        logger.info("eeeeeee");
    }

}
