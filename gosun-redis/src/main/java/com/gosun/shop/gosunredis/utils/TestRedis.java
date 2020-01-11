package com.gosun.shop.gosunredis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author lxg
 * @Date 2019/10/30
 */
@Service
public class TestRedis {

    @Autowired
    RedisTemplate redisTemplate;
    public void test(){
        redisTemplate.opsForValue().set("key2","value2");

        redisTemplate.opsForValue().get("key2");


    }

}
