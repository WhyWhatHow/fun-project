package com.fun.system.controller;

import com.fun.system.service.UserRoleService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (UserRole)表服务控制层
 *
 * @author  whywhathow
 * @since 2022-03-15 13:08:15
 */
@Api(tags = "(UserRole)") 
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("userRole")
public class UserRoleController {
    @Resource
    private final UserRoleService userRoleService;

}
