package com.fun.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.common.core.domain.R;
import com.fun.system.api.entity.UserRole;
import com.fun.system.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author whywhathow
 * @since 2022-04-08 21:53:14
 */
@Tag(name = "UserRole")
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("userRole")
public class UserRoleController {
    @Resource
    private final UserRoleService userRoleService;

    /**
     * 分页查询
     *
     * @param page     分页对象
     * @param userRole userRole
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R Page(Page page, UserRole userRole) {
        return R.ok(userRoleService.page(page, Wrappers.query(userRole)));
    }


    /**
     * 通过id查询userRole
     *
     * @param userId id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{userId}")
    public R getById(@PathVariable("userId") Long userId) {
        return R.ok(userRoleService.getById(userId));
    }

    /**
     * 通过id删除userRole
     *
     * @param userId id
     * @return R
     */
    @Operation(summary = "通过id删除userRole", description = "通过id删除userRole")
    @DeleteMapping("/{userId}")
    public R removeById(@PathVariable Long userId) {
        return R.ok(userRoleService.removeById(userId));
    }

    /**
     * 新增userRole
     *
     * @param userRole userRole
     * @return R
     */
    @Operation(summary = "新增userRole", description = "新增userRole")
    @PostMapping
    public R save(@RequestBody UserRole userRole) {
        return R.ok(userRoleService.save(userRole));
    }

    /**
     * 修改userRole
     *
     * @param userRole userRole
     * @return R
     */
    @Operation(summary = "修改userRole", description = "修改userRole")
    @PutMapping
    public R updateById(@RequestBody UserRole userRole) {
        return R.ok(userRoleService.updateById(userRole));
    }


}

