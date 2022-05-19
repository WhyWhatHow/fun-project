package com.fun.gateway.filter;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.anji.captcha.util.StringUtils;
import com.fun.common.core.constants.OauthConstant;
import com.fun.common.core.domain.RCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.Assert;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.List;

/**
 * hint : 全局filter -> XXXfilter ,某个模块的filter XXXGatewayFilterFactory
 * @program: fun-project
 * @description: 验证码过滤器  -> 处理fun-auth 模块
 * @author: WhyWhatHow
 * @link: https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#writing-custom-gatewayfilter-factories
 * @create: 2022-05-12 11:56
 **/
@Slf4j
// 如果按照官网写的话, 不要用下面这个注释
//@RequiredArgsConstructor

public class VerifyCodeGatewayFilterFactory  extends AbstractGatewayFilterFactory<VerifyCodeGatewayFilterFactory.Config> {



    final CaptchaService service ;

//    public VerifyCodeGatewayFilterFactory() {
//        super(Config.class);
//    }
    public VerifyCodeGatewayFilterFactory(CaptchaService service) {
        super(Config.class);
        this.service =service;
    }

    static final String OAUTH_TOKEN = "/oauth/token";
    static final String REFRESH_TOKEN ="refresh_token";

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange,chain)->{
            log.warn("[verify-code-filter] ==> start");
            ServerHttpRequest request = exchange.getRequest();
            //1. 判断是否是 认证请求,
            boolean isAuth = OAUTH_TOKEN.equals(request.getURI().getPath());
            if (!isAuth) {
                // 不是认证请求放行
             return  chain.filter(exchange);
            }
            String grantType = request.getQueryParams().getFirst("grant_type");

            Assert.requireNonEmpty(grantType,"GrantType 不能为空");

            if ( OauthConstant.GRANT_PASSWORD.equals(grantType)) {
                //2. password 模式  验证码认证
                boolean verified = checkVerifyCode(request);
                if (!verified) {
                    throw new RuntimeException(
                            RCode.ERROR_CAPTCHA.msg
//                        R.failed(RCode.ERROR_CAPTCHA)
                    );
                }
            }


            //3.  其他直接放行

            log.warn("[verifycation-filter - fun-auth]-->finished");
            return chain.filter(exchange);
        };
    }

    /**
     * 验证码校验逻辑
     * @param request req
     * @return
     */
    private boolean checkVerifyCode(ServerHttpRequest request) {
        String code = request.getQueryParams().getFirst("code");
        if (StringUtils.isNotBlank(code)) {
            CaptchaVO captchaVO = new CaptchaVO();
            captchaVO.setCaptchaVerification(code);
            ResponseModel res = service.verification(captchaVO);
            return res.isSuccess();
        }
        return false ;
    }

    /**
     * 是否开启verifyCode 验证码 ,true or false
     */
    public static final  String ENABLE ="enable";
//    TODO [whywhathow] [19/5/2022] [think] 是否需要可配置化, 以及是否需要进行client 过滤
    public static class Config{


    public boolean isEnable() {

            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        /**
         * 是否启用captcha 验证功能, 默认启用 | true or false
         */
        private boolean enable =true;

    public List<String> getIgnoredClients() {
        return ignoredClients;
    }

    public void setIgnoredClients(List<String> ignoredClients) {
        this.ignoredClients = ignoredClients;
    }

    private List<String> ignoredClients;

    }
}

