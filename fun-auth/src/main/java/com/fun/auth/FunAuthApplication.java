package com.fun.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: fun-project
 * @description: auth 服务启动类
 * @author: WhyWhatHow
 * @create: 2022-03-01 12:03
 **/
@SpringBootApplication
@MapperScan("com.fun.auth.mapper")
public class FunAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(FunAuthApplication.class,args);
    }
}
