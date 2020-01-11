package com.gosun.shop.gosunhrt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //开启熔断和降级
public class GosunHrtApplication {

    public static void main(String[] args) {
        SpringApplication.run(GosunHrtApplication.class, args);
    }

}
