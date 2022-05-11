package com.fun.gateway.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @program: fun-project
 * @description: 验证码接口
 * @author: WhyWhatHow
 * @create: 2022-05-11 07:43
 **/
@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("code")
public class VerifyCodeController {

    CaptchaService captchaService;

    /**
     * 获取滑块验证码
     *
     * @return 滑块验证码
     */
    @GetMapping
    public ResponseModel code() {
        ResponseModel responseModel = captchaService.get(new CaptchaVO());
        System.out.println(responseModel);
        return responseModel;
    }

    @PostMapping("/check")
    public ResponseModel checkCode(@RequestBody CaptchaVO data) {
        return captchaService.check(data);
    }


}

