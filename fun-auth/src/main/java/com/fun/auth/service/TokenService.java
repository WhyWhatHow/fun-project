package com.fun.auth.service;

import com.fun.common.core.domain.R;
import org.springframework.stereotype.Service;

/**
 * @program: fun-project
 * @description: token 服务具体实现类
 * @author: WhyWhatHow
 * @create: 2022-03-14 22:37
 **/
//@Service
public interface TokenService {
    public R login(String username, String password);

    R register();

    R refreshToken();
}
