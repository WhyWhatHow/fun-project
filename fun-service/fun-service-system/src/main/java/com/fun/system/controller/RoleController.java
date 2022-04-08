package com.fun.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.common.core.domain.R;
import com.fun.system.api.entity.Role;
import com.fun.system.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author whywhathow
 * @since 2022-04-08 21:53:16
 */
@Tag(name = "Role")
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("role")
public class RoleController {
    @Resource
    private final RoleService roleService;

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @param role role
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R Page(Page page, Role role) {
        return R.ok(roleService.page(page, Wrappers.query(role)));
    }


    /**
     * 通过id查询role
     *
     * @param roleId id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{roleId}")
    public R getById(@PathVariable("roleId") Long roleId) {
        return R.ok(roleService.getById(roleId));
    }

    /**
     * 通过id删除role
     *
     * @param roleId id
     * @return R
     */
    @Operation(summary = "通过id删除role", description = "通过id删除role")
    @DeleteMapping("/{roleId}")
    public R removeById(@PathVariable Long roleId) {
        return R.ok(roleService.removeById(roleId));
    }

    /**
     * 新增role
     *
     * @param role role
     * @return R
     */
    @Operation(summary = "新增role", description = "新增role")
    @PostMapping
    public R save(@RequestBody Role role) {
        return R.ok(roleService.save(role));
    }

    /**
     * 修改role
     *
     * @param role role
     * @return R
     */
    @Operation(summary = "修改role", description = "修改role")
    @PutMapping
    public R updateById(@RequestBody Role role) {
        return R.ok(roleService.updateById(role));
    }


}

