package com.fun.services.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @program: fun-project
 * @description: 授权服务 配置类
 * @author: WhyWhatHow
 * @create: 2022-03-12 22:33
 **/
@Configuration
@Profile("simple")
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * /oauth/authorize : 授权端点。
     * /oauth/token : 令牌端点。
     * /oauth/confirm _access : 用户确认授权提交端点。
     * /oauth/error : 授权服务错误信息端点。
     * /oauth/check_token : 用于资源服务访问的令牌解析端点。
     * /oauth/token_key : 提供公有密匙的端点,如果你使用JWT令牌的话。
     * <p>
     * 配置授权服务器的安全小鑫鑫, 一般 采用默认
     * 放行 /oauth/token, /oauth/authorize
     *
     * @param security AuthorizationServerSecurityConfigurer：定义token端点的安全策略。
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    /**
     * 配置注册到 当前授权服务器的 客户端clients 信息
     * ps: 密码授权模式需要自己制定
     * client 配置, [must]
     *
     * @param clients ClientDetailsServiceConfigurer：配置注册的OAuth客户端信息，支持指定到JDBC数据库。
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        TODO [whywhathow] [2022/3/13] [must]
//       jdbc数据库连接模式 ,从 oauth-client-details 中获取设置client 信息
        clients.withClientDetails(new ClientDetailsService() {
            @Override
            public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
                return null;
            }
        });
        ////         内存设置模式
//        clients.inMemory()
//                .withClient("client-for-server")
//                .secret(passwordEncoder.encode("client-for-server"))
//                // 授权模式配置 授权码模式
//                .authorizedGrantTypes("authorization_code", "implicit")
//                .accessTokenValiditySeconds(7200)
//                .refreshTokenValiditySeconds(72000)
////               TODO [whywhathow] [2022/3/13] [must]
//                .redirectUris("http://localhost:8080/login/oauth2/code/authorzationserver")
//                .additionalInformation()
////                client 可以访问的资源服务器ID, 每个资源服务器都有一个ID
//                .resourceIds(ResourceServerConfiguration.RESOURECE_ID)
////                client  的权限
//                .authorities("ROLE_CLIENT")
////               该client 可以访问的资源范围, 依旧可以鉴权
//                .scopes("profile", "email", "phone", "aaa")
////              自动批准的scope- > 即profile 不需要用户确认批准
//                .autoApprove("profile");
//

    }

    /**
     * 配置授权服务器的 endpoints , eg: token 存储, token自定义, 授权模式等
     *
     * @param endpoints AuthorizationServerEndpointsConfigurer：定义获取code的端点（Authorization Endpoint）和
     *                  获取access_token的端点（Token Endpoints），定义token services
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//         jwt 保存 token TODO [whywhathow] [2022/3/13] [must] -> redis存储
//        endpoints.tokenStore(new RedisTokenStore());
        super.configure(endpoints);
    }

}
