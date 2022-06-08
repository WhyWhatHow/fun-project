package com.fun.common.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.client.RestTemplate;

/**
 * @program: fun-project
 * @description: 资源服务器默认配置项 - 交给内网
 * @author: WhyWhatHow
 * @create: 2022-05-27 10:59
 **/
@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    public static final String FUN_SERVICE_SYSTEM = "FUN_SERVICE_SYSTEM";

    //TODO [whywhathow] [27/5/2022] [must] resourceServer 配置类
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.
        super.configure(resources);
//         设置redisTokenStore
        resources.tokenStore(tokenStore());
        resources.tokenServices(funRemoteTokenServices());
//          tokenExtractor ->
//        resources.tokenExtractor(new BearerTokenExtractor());
        resources.resourceId(FUN_SERVICE_SYSTEM);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {

        //权限控制
        http.authorizeRequests()//登录成功就可以访问
//                 开放user远程方法调用. TODO [whywhathow] [28/5/2022] [must]  change feign, 配置
//                .antMatchers("/user/info/**", "/user/details/**").permitAll()
                //不需要登录就可以访问
                .antMatchers("/swagger-ui/**", "/v3/api-docs**").permitAll()
//                 test 模块
                .antMatchers("/test/**").permitAll();
//                其它路径需要根据指定的方法判断是否有权限访问，基于权限管理模型认证
//                .anyRequest().access("@rbacService.hasPerssion(request,authentication)");
    }

    private final RedisConnectionFactory connectionFactory;

    /**
     * access_token 存储方案
     *
     * @return
     */
    @Bean
    TokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

//    @Value("${security.oauth2.resource.token-info-uri:http://fun-auth/oauth/check_token}")
//    private String tokenEndPointUri;
//
//    private String clientId;
//
//    private String clientSecret;


    private final ResourceServerProperties resource;

    /**
     * 创建 remoteTokenServices , 用来校验accessToken 是否真实有效
     * <p>
     * 首先，Token 可以保存在数据库或 Redis 中，资源服务器和授权服务器共享底层的 TokenStore 来验证；
     * <p>
     * 然后，资源服务器可以使用 RemoteTokenServices，来从授权服务器的 /oauth/check_token 端点进行 Token 校验。
     *
     * @return remoteTokenServices
     * @link https://zq99299.github.io/note-book/oath2/02/05.html#%E6%90%AD%E5%BB%BA%E5%8F%97%E4%BF%9D%E6%8A%A4%E8%B5%84%E6%BA%90%E6%9C%8D%E5%8A%A1%E5%99%A8
     * @see ResourceServerTokenServicesConfiguration
     */
    @Bean
    @Primary
    RemoteTokenServices funRemoteTokenServices() {
        log.info("[redisTokenStore + remoterTokenServices] --start creating ...");
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(resource.getTokenInfoUri());
        remoteTokenServices.setClientId(resource.getClientId());
        remoteTokenServices.setClientSecret(resource.getClientSecret());
        remoteTokenServices.setRestTemplate(funRestTemplate());
        return remoteTokenServices;
    }

    @Bean(name = "funRestTemplate")
    @LoadBalanced
    RestTemplate funRestTemplate() {
        return new RestTemplate();
    }
}
