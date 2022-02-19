package com.fun.demo.controller;

import com.fun.common.core.domain.R;
import com.fun.common.core.domain.RCode;
import com.fun.common.core.utils.RUtils;
import com.fun.common.web.exception.ServiceException;
import com.fun.demo.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;


/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-02-15 20:13
 **/
@RestController
@Slf4j
@Validated
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
        Assert.notNull(user, "参数不能为空");
// 3. 测试 业务异常信息
        if (user == null) {
//            throw  new ServiceException(RCode.SERVER_EXCEPTION.code,RCode.SERVER_EXCEPTION.msg);
            throw new ServiceException(RCode.SERVER_EXCEPTION);
        }
        return RUtils.createSucc(name);
    }

    /**
     * post请求, 查看参数校验异常
     *
     * @param student
     * @return
     */
    @PostMapping("/test/add/")
    public R testValidate(@RequestBody @Validated Student student) {
        System.out.println(student);
        return RUtils.createSucc(student);
    }

    /**
     * 测试 get reqeust 请求, 参数校验 , 处理
     *
     * @param account
     * @return
     */
    @GetMapping("/test/get")
    public R testGetReqVal(@Length(min = 6, max = 20) @NotNull String account) {
        return RUtils.createSucc(account);
    }

    @PostMapping("/valid")
    public R testBAValid(@RequestBody @Validated Student stu) {
        return RUtils.createSucc(stu);
    }

}
