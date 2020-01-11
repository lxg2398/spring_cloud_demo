package com.gosun.shop.gosunhrt.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestConfiguration
 * @Description 配置restTemplate
 * @Author lxg
 * @Date 2019/8/12 16:56
 * @Version 1.0
 */
@Configuration
public class RestConfiguration {
    /**
     * @Author:lxg
     * @Description: 手动将restTemplate注册到spring容器中。
     * @Date:2019/8/12 16:58
     * @Param:[]
     * @return:org.springframework.web.client.RestTemplate
     */
    @Bean
    @LoadBalanced //启用了调用服务的负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
