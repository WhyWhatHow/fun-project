package com.fun.auth.config;

import com.fun.auth.service.FunClientDetailService;
import com.fun.auth.service.FunTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * @program: fun-project
 * @description: Authorization-Server 授权服务 默认配置类 ,
 * 参考默认配置类 , {@link org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration}
 * @author: WhyWhatHow
 * @create: 2022-03-14 22:44
 * @see
 **/
@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
@Slf4j
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;

    private final AuthenticationManager authenticationManager;

    private final TokenStore redisTokenStore;
    /**
     * @see FunClientDetailService
     */
    private ClientDetailsService clientDetailsService;

//    /**
//     * @see FunTokenService
//     */
//    private TokenService tokenService;


    /**
     * 认证服务器的安全配置
     *
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
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // tokenController 放行的http.get ,http.post 方法
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenServices(tokenService) fixme
                .tokenStore(redisTokenStore)
//                .tokenEnhancer(tokenEnhancer())
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false) //允许使用refreshToken
                // 转发 /oauth/confirm_access 的req 转发到 /token/confirm_access
                .pathMapping("/oauth/confirm_access", "/token/confirm_access")
// TODO [whywhathow] [2022/3/15] [must] 异常处理自定义模式
// 异常处理 @see DefaultWebResponseExceptionTranslator
//                .exceptionTranslator()
        ;
//        //配置token的存储方式
//        endpoints
//                .tokenStore(tokenStore)
//                //身份验证管理器
//                .authenticationManager(authenticationManager)
//                //配置用户数据源
//                .userDetailsService(userDetailsService);

    }


}
