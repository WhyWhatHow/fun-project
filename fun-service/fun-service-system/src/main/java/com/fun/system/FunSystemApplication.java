package com.fun.system;

import com.fun.common.security.annotation.EnableFunResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: fun-project
 * @description: 后台管理模块启动类
 * @author: WhyWhatHow
 * @create: 2022-03-15 12:54
 **/
@MapperScan({"com.fun.system.api.mapper", "com.fun.system.mapper"})
@EnableFeignClients(value = "com.fun")
@EnableFunResourceServer
@SpringBootApplication
@EnableCaching
public class FunSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(FunSystemApplication.class, args);

    }
}
