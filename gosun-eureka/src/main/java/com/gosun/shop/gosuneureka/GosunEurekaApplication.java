package com.gosun.shop.gosuneureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GosunEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GosunEurekaApplication.class, args);
    }

}
