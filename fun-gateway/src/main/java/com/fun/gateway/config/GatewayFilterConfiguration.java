package com.fun.gateway.config;

import com.anji.captcha.service.CaptchaService;
import com.fun.gateway.filter.AuthFilter;
import com.fun.gateway.filter.VerifyCodeGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: fun-project
 * @description: 过滤器配置类
 * @author: WhyWhatHow
 * @create: 2022-03-04 09:01
 **/
@Configuration
public class GatewayFilterConfiguration {
    /**
     * 认证 全局 过滤器
     * @return
     */
    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    /**
     * 注册 验证码filter
     * @param service 验证码验证service
     * @return VerifyCodeGatewayFilterFactory
     */
    @Bean
    public VerifyCodeGatewayFilterFactory verifyCodeFilter(CaptchaService service){
        return new VerifyCodeGatewayFilterFactory(service);
    }


}
