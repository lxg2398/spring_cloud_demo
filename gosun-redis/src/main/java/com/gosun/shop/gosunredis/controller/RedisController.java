package com.gosun.shop.gosunredis.controller;

import com.gosun.shop.gosunredis.service.RedisService;
import com.gosun.shop.gosunredis.utils.TestRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author lxg
 * @Date 2019/11/1
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private TestRedis testRedis;

    @Autowired
    private RedisService redisService;

    @RequestMapping("test")
    public void test(){
        testRedis.test();
    }

    @RequestMapping("testString")
    public void testString(){
        redisService.testString();
    }
}
