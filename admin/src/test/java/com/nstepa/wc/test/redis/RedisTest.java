package com.nstepa.wc.test.redis;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.nstepa.wc.admin.Application;
import com.nstepa.wc.beans.UploadStinky;
import com.nstepa.wc.commons.utils.BeanUtils;
import com.nstepa.wc.commons.utils.JsonUtils;
import com.nstepa.wc.redis.utils.RedisUtils;
import com.nstepa.wc.test.common.JosnUtilsTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@ActiveProfiles({"henry"})
public class RedisTest {
    public Logger logger = LoggerFactory.getLogger(JosnUtilsTest.class);

    @Resource
    private RedisUtils redisUtils;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testadd(){
        logger.info("hhe");
        redisUtils.set("key2","value2");
        String key2 = (String) redisUtils.get("key2");
        logger.info(key2);
        logger.info("eeeeeee");
    }

    @Test
    public void hsetTest(){
        UploadStinky uploadStinky = new UploadStinky();
        uploadStinky.setCreateTime(new Date());
        uploadStinky.setFeelType(1);
        uploadStinky.setUserId(122);
        uploadStinky.setNote("hello");
        redisUtils.hset("456", "henry", JsonUtils.convertObjectToJSON(uploadStinky), 50);
        redisUtils.hset("456", "henry1","haha");
        redisUtils.set("123", "hello", 50);
    }
    @Test
    public void msetTest(){

        UploadStinky uploadStinky = new UploadStinky();
        uploadStinky.setCreateTime(new Date());
        uploadStinky.setFeelType(1);
        uploadStinky.setUserId(122);
        uploadStinky.setNote("hello");


        Map<String,Object> map = new HashMap<>();
        map.put("feelType",1);
        map.put("note", "note");
        map.put("2e", "23");
        redisUtils.hmset("999", map, 60);
//        redisUtils.hmset("666", BeanUtils.convertBean(uploadStinky), 60);
        redisUtils.hmset("777_aa", BeanUtils.transBean2Map(uploadStinky), 600);
        redisUtils.hmset("666_aa", BeanUtils.transBean2Map(uploadStinky), 600);
        redisUtils.hmset("555_aa", BeanUtils.transBean2Map(uploadStinky), 600);

//        redisUtils.hmset("888_aa", BeanUtils.beanToMap(uploadStinky), 60);
//        redisUtils.hmset("henry",map,100);
        logger.info(uploadStinky.toString());
    }

    @Test
    public void search(){
        String key_like = "*_aa";
        Set<String> keys = redisTemplate.keys(key_like);
        keys.forEach(t->{
            logger.info("键值--->"+t);
        });
    }

}
