package com.fun.common.web.config;

import com.fun.common.web.apiversion.annotation.EnableApiVersion;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
@EnableDiscoveryClient // 开启服务注册
public class WebAutoConfiguration {

}
