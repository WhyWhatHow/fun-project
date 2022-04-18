package com.fun.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.common.core.domain.R;
import com.fun.system.api.dto.UserRequest;
import com.fun.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author whywhathow
 * @since 2022-04-08 21:53:15
 */
@Tag(name = "User" ,description = "用户管理模块")
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("user")
public class UserController {
    @Resource
    private final UserService userService;
    @GetMapping("/details/{username}")
    @Operation(summary = "通过用户名获取用户所有信息" ,description = "通过用户名获取用户所有信息")
    public R getUserDetailsByUsername(@PathVariable("username")@NotBlank String username){
        return R.ok(userService.getUserDetailsByUsername(username));
    }

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @param user user
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R Page(Page page, UserRequest user) {
        return R.ok(userService.page(page, Wrappers.query(user)));
    }


    /**
     * 通过id查询user
     *
     * @param userId id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{userId}")
    public R getById(@PathVariable("userId")@NotNull Long userId) {
        return R.ok(userService.getById(userId));
    }

    /**
     * 通过id删除user
     *
     * @param userId id
     * @return R
     */
    @Operation(summary = "通过id删除user", description = "通过id删除user")
    @DeleteMapping("/{userId}")
    public R removeById(@PathVariable @NotNull Long userId) {
        return R.ok(userService.removeByUserId(userId));
    }

    /**
     * 新增user
     *
     * @param user user
     * @return R
     */
    @Operation(summary = "新增user", description = "新增user")
    @PostMapping
    public R save(@RequestBody@Valid UserRequest user) {
        return R.ok(userService.saveUser(user));
    }

    /**todo
     * 修改user
     *
     * @param user user
     * @return R
     */
    @Operation(summary = "修改user", description = "修改user")
    @PutMapping
    public R update(@RequestBody @Valid UserRequest user) {
        return R.ok(userService.update(user));
    }


    @Operation(description = "通过用户名获取用户权限信息")
    @GetMapping("/info/{username}")
    public R getUserInfoByUsername(@PathVariable("username") @NotBlank String username){
        return R.ok(userService.getUserInfoByUsername(username));
    }


}

