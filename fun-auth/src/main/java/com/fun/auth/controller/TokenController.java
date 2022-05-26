package com.fun.auth.controller;

import com.fun.auth.service.FunTokenService;
import com.fun.common.core.domain.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * TODO [whywhathow] [24/5/2022] [must]  第三方应用配置
 *
 * @program: fun-project
 * @description: 用户登录信息 已token返回给第三方应用, 应用可以凭借token获取用户权限,访问用户数据
 * @author: WhyWhatHow
 * @create: 2022-03-04 14:18
 **/
@RestController
@RequestMapping("/token")
@Validated
@Deprecated
public class TokenController {

//    @Autowired
    FunTokenService tokenService;
//    TODO [whywhathow] [2022/3/14] [must] 参数校验 ,@valid 实现

    /**
     * 用户登录 ,返回token 给第三方应用
     *
     * @return
     */

    @PostMapping("/login")
    public R login(@NotBlank String username, @NotBlank String password) {

        return tokenService.login(username, password);
    }

    /**
     * 用户注册,
     * TODO [whywhathow] [2022/3/14]  [opt] 邮箱验证
     * 用户 实体类
     *
     * @return
     */
    @PostMapping("/register")
    public R register() {
        return tokenService.register();
    }

    /**
     * token 刷新机制
     *
     * @return
     */
    @GetMapping("/refresh")
    public R refreshToken() {
        return tokenService.refreshToken();
    }


}
