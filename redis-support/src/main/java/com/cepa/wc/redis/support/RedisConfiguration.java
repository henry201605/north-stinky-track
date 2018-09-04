package com.cepa.wc.redis.support;

import com.cepa.wc.commons.utils.CepaStrings;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

@Configuration
public class RedisConfiguration {
    private RedisSerializer<Object> strSerializer = new RedisSerializer<Object>(){

        @Override
        public byte[] serialize(Object o) throws SerializationException {
            return CepaStrings.bytes(o);
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            return ArrayUtils.isEmpty(bytes) ? null : new String(bytes,StandardCharsets.UTF_8);
        }
    };

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        setSerializer(template);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        setSerializer(template);
        return template;
    }

    private void setSerializer(RedisTemplate<?,?> tmpl) {
        tmpl.setKeySerializer(strSerializer);
        tmpl.setValueSerializer(strSerializer);
        tmpl.setHashValueSerializer(strSerializer);
        tmpl.setHashKeySerializer(strSerializer);
    }
}
