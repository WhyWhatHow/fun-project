package com.fun.system.controller;

import com.fun.system.service.UserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * (User)表服务控制层
 *
 * @author  whywhathow
 * @since 2022-03-15 13:08:09
 */
@Api(tags = "(User)") 
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {
    @Resource
    private final UserService userService;

}
