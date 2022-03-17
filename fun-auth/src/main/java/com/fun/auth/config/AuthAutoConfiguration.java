package com.fun.auth.config;

import com.fun.auth.service.FunClientDetailService;
import com.fun.auth.service.FunTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @program: fun-project
 * @description: bean注入
 * @author: WhyWhatHow
 * @create: 2022-03-17 22:41
 **/
@Configuration
public class AuthAutoConfiguration {
    /**
     * 注入 redisTokenStore ,用来存储access_token
     * todo -> 继承 REdisTokenStore ,添加自定义方法即可
     *
     * @return
     */
    @Bean
    TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * token 生成接口输出增强
     *
     * @return
     */
    @Bean
    TokenEnhancer tokenEnhancer() {
        return null;
    }

    /**
     * @param tokenStore            token 保存位置
     * @param clientDetailsService  Oauth2 client
     * @param authenticationManager 认证管理器
     * @return
     */
    @Bean
    FunTokenService tokenService(TokenStore tokenStore, ClientDetailsService clientDetailsService, AuthenticationManager authenticationManager) {
        FunTokenService tokenService = new FunTokenService();
        tokenService.setTokenStore(tokenStore);
        tokenService.setClientDetailsService(clientDetailsService);
        tokenService.setSupportRefreshToken(true);//
        tokenService.setAccessTokenValiditySeconds(60 * 60 * 24); // access_token 有效期 1day
        tokenService.setRefreshTokenValiditySeconds(60 * 60 * 48); // refresh_token 有效期
        tokenService.setAuthenticationManager(authenticationManager);
//        TODO [whywhathow] [2022/3/17] [must] tokenEnhancer
//tokenService.setTokenEnhancer();
        return tokenService;

    }

    /**
     * 注入 自定义clientDetailsService
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

