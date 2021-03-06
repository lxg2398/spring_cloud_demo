package com.gosun.shop.gosunitem;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDistributedTransaction //开启分布式事务
public class GosunItemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GosunItemsApplication.class, args);
    }

}
