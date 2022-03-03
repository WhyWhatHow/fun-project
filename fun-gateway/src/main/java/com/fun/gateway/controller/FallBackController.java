package com.fun.gateway.controller;

import com.fun.common.core.domain.R;
import com.fun.common.core.domain.RCode;
import com.fun.common.core.utils.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: fun-project
 * @description: 服务降级controller
 * @author: WhyWhatHow
 * @create: 2022-03-02 22:40
 **/
@RestController
@Slf4j
public class FallBackController {
    @GetMapping("/fallback")
    public R fallback() {
//        TODO [whywhathow] [2022/3/2]   [opt] 熔断 uri 记录,以及dependency重新依赖
//        log.warn("[FallBack Hystrik]->{}",);
        return RUtils.createFail(RCode.FALLBACK);
    }
}
