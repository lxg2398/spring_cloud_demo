package com.gosun.shop.gosunconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer//该注解表示启用配置中心的配置
public class GosunConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(GosunConfigApplication.class, args);
    }

}
