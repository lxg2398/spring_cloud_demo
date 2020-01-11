package com.gosun.shop.gosunredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class GosunRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(GosunRedisApplication.class, args);
    }

}
