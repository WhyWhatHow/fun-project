package com.fun.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: fun-project
 * @description: gateway 网关启动类 ,默认端口9000
 * @author: WhyWhatHow
 * @create: 2022-03-01 10:58
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class FunGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FunGatewayApplication.class, args);
    }
}
