package com.fun.demo.controller;

import com.fun.common.core.domain.R;
import com.fun.common.core.utils.RUtils;
import com.fun.demo.domain.User;
import com.fun.demo.valid.annotation.EncryptId;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: fun-project
 * @description: https://segmentfault.com/a/1190000023471742#item-2-5
 * @author: WhyWhatHow
 * @create: 2022-02-19 16:08
 **/
@RestController
@Validated
public class UserController {
    @PostMapping("/save")
    public R saveUser(@RequestBody @Validated(User.Save.class) User user) {
        // 校验通过，才会执行业务逻辑处理
        return RUtils.createSucc(user);
    }

    @PostMapping("/update")
    public R updateUser(@RequestBody @Validated(User.Update.class) User user) {
        // 校验通过，才会执行业务逻辑处理
        return RUtils.createSucc(user);
    }

    @GetMapping("/encrypt")
    public R testEncryptId(@EncryptId String id) {
        return RUtils.createSucc(id);
    }
}
