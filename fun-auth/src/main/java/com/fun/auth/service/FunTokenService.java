package com.fun.auth.service;

import com.fun.common.core.domain.R;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * @program: fun-project
 * @description: token 服务具体实现类
 * @author: WhyWhatHow
 * @create: 2022-03-14 22:37
 **/
//@Service
public class FunTokenService extends DefaultTokenServices {
    public R login(String username, String password) {
        return null;
    }

    public R register() {
        return null;
    }

    public R refreshToken() {
        return null;
    }
}
