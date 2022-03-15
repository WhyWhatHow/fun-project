package com.fun.system.controller;

import com.fun.system.service.RoleService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Role)表服务控制层
 *
 * @author  whywhathow
 * @since 2022-03-15 13:08:12
 */
@Api(tags = "(Role)") 
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("role")
public class RoleController {
    @Resource
    private final RoleService roleService;

}
