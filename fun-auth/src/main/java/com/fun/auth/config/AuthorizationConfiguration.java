package com.fun.auth.config;

import com.fun.auth.service.FunClientDetailService;
import com.fun.auth.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * @program: fun-project
 * @description: Authorization-Server 默认配置类
 * @author: WhyWhatHow
 * @create: 2022-03-14 22:44
 * @see
 **/
@EnableAuthorizationServer
@Configuration
@Slf4j
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {
    private final DataSource dataSource;

    private final AuthenticationManager authenticationManager;

    private final TokenStore redisTokenStore;

    public AuthorizationConfiguration(DataSource dataSource, AuthenticationManager authenticationManager, TokenStore redisTokenStore) {
        this.dataSource = dataSource;
        this.authenticationManager = authenticationManager;
        this.redisTokenStore = redisTokenStore;
    }

    /**
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        放行 通过表单 来进行client认证 的request
        security.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
    }

    /**
     * Oauth2 client 配置
     *
     * @param clients oauth2 client
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(funClientDetailServices());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // tokenController 放行的http.get ,http.post 方法
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenServices(tokenService())
                .tokenStore(redisTokenStore)
                .tokenEnhancer(tokenEnhancer())
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false) //允许使用refreshToken
                // 转发 /oauth/confirm_access 的req 转发到 /token/confirm_access
                .pathMapping("/oauth/confirm_access", "/token/confirm_access")
// TODO [whywhathow] [2022/3/15] [must] 异常处理自定义模式
// 异常处理 @see DefaultWebResponseExceptionTranslator

//                .exceptionTranslator()
        ;


    }

    @Bean
    TokenEnhancer tokenEnhancer() {
        return null;
    }

    @Bean
    TokenService tokenService() {
        TokenService tokenService = new TokenService();
        return tokenService;

    }

    /**
     * 注入 client Details Service
     *
     * @return
     */
    @Bean
    FunClientDetailService funClientDetailServices() {
        FunClientDetailService clientDetailService = new FunClientDetailService();
//        clientDetailService.setSelectClientDetailsSql();
        return clientDetailService;
    }
}
