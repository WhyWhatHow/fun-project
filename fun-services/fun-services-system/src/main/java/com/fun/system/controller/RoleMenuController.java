package com.fun.system.controller;

import com.fun.system.service.RoleMenuService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (RoleMenu)表服务控制层
 *
 * @author  whywhathow
 * @since 2022-03-15 13:08:14
 */
@Api(tags = "(RoleMenu)") 
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("roleMenu")
public class RoleMenuController {
    @Resource
    private final RoleMenuService roleMenuService;

}
