package com.fun.gateway.config;

import com.fun.gateway.support.NacosRouteDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: fun-project
 * @description: nacos route 自动配置类
 * @author: WhyWhatHow
 * @create: 2022-03-02 15:39
 **/
@Configuration
public class NacosRouteConfiguration {
    /**
     * nacos + gateway 实现动态路由配置类
     * @return
     */
    @Bean
    public NacosRouteDefinitionRegistry nacosRouteDefinitionRegistry() {
        return new NacosRouteDefinitionRegistry();
    }


}
