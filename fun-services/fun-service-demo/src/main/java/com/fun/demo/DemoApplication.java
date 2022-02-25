package com.fun.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-02-15 20:12
 **/
@MapperScan("com.fun.demo.mapper")
@EnableAsync
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
//        int i = Runtime.getRuntime().availableProcessors();
        SpringApplication.run(DemoApplication.class, args);

    }
}
