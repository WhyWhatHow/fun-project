package com.fun.common.security.config;

import com.fun.common.security.properties.IgnoreUriProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import javax.annotation.Resource;

/**
 * @program: fun-project
 * @description: Security默认配置类 -> 配置  webSecurity 放行的uri  列表
 * @author: WhyWhatHow
 * @create: 2022-05-26 22:28
 **/

@RequiredArgsConstructor
@EnableConfigurationProperties(IgnoreUriProperties.class)
public class DefaultWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private final IgnoreUriProperties ignoreUriProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        ignoreUriProperties.getUris().forEach(val -> {
            registry.antMatchers((String) val).permitAll();
        });
    }

}
