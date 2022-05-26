package com.fun.auth.config;

import com.fun.auth.dto.FunUserDetails;
import com.fun.common.core.constants.OauthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;

/**
 * @program: fun-project
 * @description: bean注入
 * @author: WhyWhatHow
 * @create: 2022-03-17 22:41
 **/
@Deprecated
//@Configuration
@Slf4j
//@Order(80)
public class AuthAutoConfiguration {
    /**
     * 注入 redisTokenStore ,用来存储access_token
     * todo -> 继承 REdisTokenStore ,添加自定义方法即可
     *
     * @return
     */
//    @Bean
//    @Primary
//    TokenStore redisTokenStore(RedisConnectionFactory redisConnectionFactory) {
//        return new RedisTokenStore(redisConnectionFactory);
//    }

    /**
     * token 生成接口输出增强
     *
     * @return tokenEnhancer
     */
//    @Bean
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

//    /**
//     * @param tokenStore            token 保存位置
//     * @param clientDetailsService  Oauth2 client
//     * @param authenticationManager 认证管理器
//     * @return
//     */
//    @Bean
//    FunTokenService tokenService(TokenStore tokenStore, ClientDetailsService clientDetailsService, AuthenticationManager authenticationManager) {
//        FunTokenService tokenService = new FunTokenService();
//        tokenService.setTokenStore(tokenStore);
//        tokenService.setTokenEnhancer(tokenEnhancer());
//        tokenService.setClientDetailsService(clientDetailsService);
//        tokenService.setSupportRefreshToken(true);//
//        tokenService.setAccessTokenValiditySeconds(60 * 60 * 24); // access_token 有效期 1day
//        tokenService.setRefreshTokenValiditySeconds(60 * 60 * 48); // refresh_token 有效期
//        tokenService.setAuthenticationManager(authenticationManager);
////        TODO [whywhathow] [2022/3/17] [must] tokenEnhancer
////tokenService.setTokenEnhancer();
//        return tokenService;
//
//    }


}

