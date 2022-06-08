package com.fun.gateway.config;

import com.anji.captcha.service.CaptchaService;
import com.fun.gateway.filter.AuthHeaderFilter;
import com.fun.gateway.filter.VerifyCodeGatewayFilterFactory;
import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledFilter;
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
//    @Bean
//    public AuthFilter authFilter() {
//        return new AuthFilter();
//    }

    /**
     * 注册 验证码filter
     *
     * @param service code 校验, 用于 fun-auth 模块
     * @return VerifyCodeGatewayFilterFactory
     */
    @Bean
    @ConditionalOnEnabledFilter
    public VerifyCodeGatewayFilterFactory verifyCodeFilter(CaptchaService service) {
        return new VerifyCodeGatewayFilterFactory(service);
    }

    /**
     * 注册全局过滤器,将每一个从gateway走的网关请求, 对头部信息进行处理
     *
     * @return
     */
    @Bean
    public AuthHeaderFilter authHeaderFilter() {
        return new AuthHeaderFilter();
    }

}
