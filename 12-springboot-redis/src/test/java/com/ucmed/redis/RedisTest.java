package com.ucmed.redis;

import com.ucmed.redis.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisTemplate<String, User> userRedisTemplate;

    private User user;

    @Test
    public void set() {
        redisTemplate.opsForValue().set("myKey", "myValue");

        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

    @Before
    public void before() {
        user = new User();

        user.setId(1);
        user.setUsername("李四");
        user.setPassword("123456");
        user.setEmail("123@123.com");
    }

    @Test
    public void tsetSet() {
        userRedisTemplate.opsForValue().set("user", user);
        System.out.println(userRedisTemplate.opsForValue().get("user"));
    }
}
