package com.gosun.shop.gosunhrt.controller;

import com.alibaba.fastjson.JSON;
import com.gosun.shop.gosunhrt.vo.Orders;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName Controller
 * @Description 测试通过restTemplate 来实现服务之间通讯
 * @Author lxg
 * @Date 2019/8/12 16:38
 * @Version 1.0
 */
@RestController
@RequestMapping("test_restTemplate")
@DefaultProperties(defaultFallback = "defaultFallBack")
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{id}")
    //@HystrixCommand(fallbackMethod = "fallBack")
    @HystrixCommand(commandProperties={
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String getOrderInfo(@PathVariable("id") String id){

        //测试熔断(当熔断发生后，在10s后，会放部分请求进来，试探是否恢复正常)
        int count = 0;
        if("ddfd".equals(id)){
            count++;
            return "success"+count;
        }



        //去其他服务调用方法，获取想要的结果
        //获取url(想要的调用的其他服务的方法的url路径），直连方式（无法做到负载均衡）
        //String url = "http://localhost:9001/order/"+id;


        // （为了做到高可用有两种方式：
        // 一：可以将url替换为网关的映射path，也就能做到负载均衡了
        // 二： 引入ribbon依赖，并在注册restTemplate的bean上，加上@LoadBalanced注解，
        // 该注解表示启用了restTemplate调用服务的负载均衡)

        //String url = "http://localhost:6001/order/order/"+id;//使用方式一负载

        //方式二，负载
        String url = "http://gosun-order/order/"+id;

        //使用restTemplate调用其他服务方法
        Orders forObject = restTemplate.getForObject(url, Orders.class);

        //使用fastjson 将对象转为jsonString
        String s = JSON.toJSONString(forObject);


        return s;
    }


//    public String fallBack(String id){
//
//        return "系统正在处理中，请稍后...";
//    }

    public String defaultFallBack(){

        return "系统正在处理中，请稍后...";
    }
}
