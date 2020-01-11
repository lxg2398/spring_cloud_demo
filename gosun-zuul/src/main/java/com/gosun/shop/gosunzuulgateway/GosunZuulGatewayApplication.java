package com.gosun.shop.gosunzuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableEurekaClient
@EnableZuulProxy //启用网关代理
public class GosunZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GosunZuulGatewayApplication.class, args);
    }

}
