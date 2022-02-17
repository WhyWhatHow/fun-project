package com.fun.common.web.config;

import com.fun.common.web.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: fun-project
 * @description: spring mvc的默认配置文件
 * @author: WhyWhatHow
 * @create: 2022-02-16 21:56
 **/
@Configuration
public class WebAutoConfiguration {
    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }
}
