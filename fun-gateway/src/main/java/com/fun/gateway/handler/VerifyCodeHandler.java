package com.fun.gateway.handler;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @program: fun-project
 * @description: 验证码 处理器 --> 逻辑处理模块
 * @author: WhyWhatHow
 * @create: 2022-05-09 16:34
 **/
@Slf4j
@Deprecated
public class VerifyCodeHandler implements HandlerFunction<ServerResponse> {

    CaptchaService captchaService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
//        TODO [whywhathow] [9/5/2022] [must]
//        return null;
        ResponseModel responseModel = captchaService.get(new CaptchaVO());
        log.warn("[code1]->{}", responseModel);
        return ServerResponse.status(HttpStatus.OK)
                .bodyValue(responseModel)

                ;
    }
}
