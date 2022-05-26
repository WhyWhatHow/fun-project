package com.fun.auth.config;

import com.fun.auth.dto.FunUserDetails;
import com.fun.auth.service.FunClientDetailsService;
import com.fun.common.core.constants.OauthConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;

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
//@ConditionalOnBean(value = SecurityConfiguration.class)
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {
    private final DataSource dataSource;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final RedisConnectionFactory connectionFactory;
    /**
     * 认证服务器的安全配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        放行 通过表单 来进行client认证 的request
//        TODO [whywhathow] [2022/4/28] [must] clients 认证流程 -> eg  gitee 如何接入, github如何接入?
//        security
//                //设置令牌申请需要认证 - /oauth/token_key
//                .tokenKeyAccess("isAuthenticated()")
//                //设置校验令牌不需要认证 - /oauth/check_token
//                .checkTokenAccess("permitAll()")
//                //让/oauth/token支持client_id以及client_secret作登录认证
//                .allowFormAuthenticationForClients();
        security.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
    }


    /**
     * Oauth2 client 配置
     * 默认只支持http basic认证
     *
     * @param clients oauth2 client
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//      TODO [whywhathow] [24/5/2022] [must]  改成从db中读取
        clients.withClientDetails(funClientDetailsService(dataSource));

//        内存配置
//        clients.inMemory()
//                .withClient("fun").secret("fun")
//        ;

    }

    /**
     * 配置Token令牌的存储以及相关属性
     *
     * @param endpoints 授权服务配置类
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // tokenController 放行的http.get ,http.post 方法
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenServices(tokenService) fixme
                .tokenStore(redisTokenStore()) //TODO [whywhathow] [2022/4/29] [must] 自定义实现. ->ps: token 先enhancer ,再store
                .tokenEnhancer(tokenEnhancer())
//                .tokenEnhancer(tokenEnhancer())
                .authenticationManager(authenticationManager)

                .userDetailsService(userDetailsService)  //配置用户数据源
                .reuseRefreshTokens(false) //允许使用refreshToken
        // 转发 /oauth/confirm_access 的req 转发到 /token/confirm_access
//                .pathMapping("/oauth/confirm_access", "/token/confirm_access")
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

    /**
     * accessToken 存储方案 .
     *
     * @return
     */
    @Bean
    TokenStore redisTokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            HashMap<String, Object> additionalInformation = new HashMap<>(4);
            String grantType = authentication.getOAuth2Request().getGrantType();
            additionalInformation.put(OauthConstant.DETAILS_LICENSE, OauthConstant.PROJECT_LICENSE);
            additionalInformation.put(OauthConstant.CLIENT_ID, authentication.getOAuth2Request().getClientId());

            // client 模式
            if (OauthConstant.CLIENT_CREDENTIALS.equals(grantType)) { // 客户端模式,直接返回accessToken
                log.info("[TokenEnhancer-客户端模式]-> {} ", OauthConstant.CLIENT_CREDENTIALS);
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                return accessToken;
            }

            // password 模式
            if (OauthConstant.GRANT_PASSWORD.equals(grantType)) {
                log.info("[TokenEnhancer-密码模式]-> {} ", OauthConstant.CLIENT_CREDENTIALS);
                FunUserDetails userDetails = (FunUserDetails) authentication.getUserAuthentication().getPrincipal();
                additionalInformation.put(OauthConstant.DETAILS_USER, userDetails);
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                return accessToken;
            }
            return accessToken;
        };
    }


    /**
     * oauth_client_details 表名称.
     */
    @Value("${fun.security.oauth.client.tableName:sys_oauth_client_details}")
    private String oauthClientTableName;

    /**
     * 注入 自定义clientDetailsService
     *
     * @return clientDetailService
     */
    @Bean
    ClientDetailsService funClientDetailsService(DataSource dataSource) {
        FunClientDetailsService funClientDetailsService =
                new FunClientDetailsService(dataSource, oauthClientTableName);
        return funClientDetailsService;
    }


}
