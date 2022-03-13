package com.fun.services.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @program: fun-project
 * @description: resource server 配置类 ,
 * 资源服务器 用来对Oauth2客户端client 的 access_token 进行鉴权
 * 该类用来配置 可用于 client 访问的端点信息, 其他断点信息有SecurityConfiguration 配置
 * @author: WhyWhatHow
 * @create: 2022-03-13 09:30
 **/
@Profile("simple")
@Configuration
@EnableResourceServer  // 标记 当前服务为 资源服务器
@Slf4j
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    //   TODO [whywhathow] [2022/3/13] [must]  修改为根据spring.application.name 进行配置
    public static final String RESOURECE_ID = "authorizationServer";

    /**
     * 授权服务器,资源服务器在 同一应用中,->使用默认DefaultTokenServices 进行验证
     * 授权服务器 ,资源服务器 两个应用时,
     *           access_token 类型是 Opaque: RemoteTokenServices
     *           access_token 类型是JWT 时, 使用DefaultTokenServices(DefaultTokenServices的tokenStore 需要设置JWTTokenStore)
     * @param resources 配置ResourceServerTokenServices、resourceId等
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        super.configure(resources);
        resources.resourceId(RESOURECE_ID);
    }

    /**
     * 对 /me 创建新的过滤器链
     * @see org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        log.warn("[ ResourceServerConfiguration ]  配置 httpsecurity" );
//         只有 /me 端点作为资源服务器的资源 OAuth2AuthenticationProcessingFilter过滤器，
        http.requestMatchers().antMatchers("/me")
                .and()  // 返回httpSecurity , 配置其他 断点 .
//                放行其他请求
                .authorizeRequests()
                .anyRequest().authenticated();
//        http.authorizeRequests().anyRequest().authenticated();
    }

}
