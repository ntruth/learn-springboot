package com.ucmed.redis.config;

import com.ucmed.redis.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *
 */
@Configuration
public class RedisConfig {

//    /**
//        自定义Redis序列化器
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        // 关联
//        template.setConnectionFactory(factory);
//        // 设置key的序列化器
//        template.setKeySerializer(new StringRedisSerializer());
//        // 设置value的序列化器
//        template.setValueSerializer(new StringRedisSerializer());
//
//        return template;
//    }

    /**
     * 配置USER的自定义序列化器
     */
    @Bean
    public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));

        return template;
    }
}
