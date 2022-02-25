package com.fun.common.web.config;

import com.fun.common.web.apiversion.ApiVersionHandlerMapping;
import com.fun.common.web.apiversion.annotation.EnableApiVersion;
import com.fun.common.web.apiversion.config.ApiVersionRegistrations;
import com.fun.common.web.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: fun-project
 * @description: spring mvc的默认配置文件
 * @author: WhyWhatHow
 * @create: 2022-02-16 21:56
 **/
@ComponentScan("com.fun")
@Configuration
@EnableApiVersion
public class WebAutoConfiguration {

}
