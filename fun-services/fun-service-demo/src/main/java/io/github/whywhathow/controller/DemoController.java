package io.github.whywhathow.controller;

import io.github.whywhathow.domain.R;
import io.github.whywhathow.domain.RCode;
import io.github.whywhathow.exception.ServiceException;
import io.github.whywhathow.utils.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-02-15 20:13
 **/
@RestController
@Slf4j
public class DemoController {
    @GetMapping("/test")
    public R test(String name) {
        name = " hello, fun_project" + name;
        log.info("[/test/]" + name);
        return RUtils.createSucc(name);
    }

    @Value("${com.name}")
    String name;

    @GetMapping("/mult/env")
    public R testMultEnv() {
        name = " now in " + name;
        return RUtils.createSucc(name);
    }

    @GetMapping("/testEx")
    public R testException(String name) {
        name = " hello, fun_project" + name;
//        1.测试默认异常处理机制
//        System.out.println(1/0);
        String user = null;
//        2. 测试 assert 断言异常请求
//        Assert.isNull(name, "参数不能为空");
// 3. 测试 业务异常信息
        if (user == null) {
//            throw  new ServiceException(RCode.SERVER_EXCEPTION.code,RCode.SERVER_EXCEPTION.msg);
            throw new ServiceException(RCode.SERVER_EXCEPTION);
        }
        return RUtils.createSucc(name);
    }
}
