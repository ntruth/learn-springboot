package com.ucmed.redis;

import com.alibaba.fastjson.JSONObject;
import com.ucmed.redis.model.User;
import com.ucmed.redis.utils.RedisUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
//    @Autowired
//    private RedisTemplate<String, Object> objectRedisTemplate;
//
//    @Autowired
//    private RedisTemplate<Object, Object> fastRedisTemplate;
//
//    @Autowired
//    private RedisTemplate<String, User> userRedisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    private User user;

    private List<User> users;

    @Test
    public void set() {
//        objectRedisTemplate.opsForValue().set("user", user);
//        System.out.println(objectRedisTemplate.opsForValue().get("user"));
//
//        // 存储list
//        objectRedisTemplate.opsForValue().set("users", users);
//        System.out.println(objectRedisTemplate.opsForValue().get("users"));

//        fastRedisTemplate.opsForValue().set("class", "三年二班");
//        System.out.println(fastRedisTemplate.opsForValue().get("class"));

        // 设置key-value
        redisUtils.set("a", "a");
        // 获取value
        redisUtils.get("a");
        // 删除key
        redisUtils.delete("a");
        // 判断是否含有相应的key
        redisUtils.hasKey("a");

        // 设置key-vlue,并设置过期时间
        redisUtils.setEx("user", JSONObject.toJSONString(user), 5, TimeUnit.SECONDS);
        System.out.println(redisUtils.get("user"));
        //redisUtils.delete("user");

    }

    @Before
    public void before() {
        user = new User();

        user.setId(1);
        user.setUsername("李四");
        user.setPassword("123456");
        user.setEmail("123@123.com");
        users = new ArrayList<>();
        users.add(user);
    }

    @Test
    public void tsetSet() {
//        userRedisTemplate.opsForValue().set("user", user);
//        System.out.println(userRedisTemplate.opsForValue().get("user"));
    }
}
