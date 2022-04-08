package com.fun.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.common.core.domain.R;
import com.fun.system.api.entity.RoleMenu;
import com.fun.system.service.RoleMenuService;
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
@Tag(name = "RoleMenu")
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("roleMenu")
public class RoleMenuController {
    @Resource
    private final RoleMenuService roleMenuService;

    /**
     * 分页查询
     *
     * @param page     分页对象
     * @param roleMenu roleMenu
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R Page(Page page, RoleMenu roleMenu) {
        return R.ok(roleMenuService.page(page, Wrappers.query(roleMenu)));
    }


    /**
     * 通过id查询roleMenu
     *
     * @param roleId id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{roleId}")
    public R getById(@PathVariable("roleId") Long roleId) {
        return R.ok(roleMenuService.getById(roleId));
    }

    /**
     * 通过id删除roleMenu
     *
     * @param roleId id
     * @return R
     */
    @Operation(summary = "通过id删除roleMenu", description = "通过id删除roleMenu")
    @DeleteMapping("/{roleId}")
    public R removeById(@PathVariable Long roleId) {
        return R.ok(roleMenuService.removeById(roleId));
    }

    /**
     * 新增roleMenu
     *
     * @param roleMenu roleMenu
     * @return R
     */
    @Operation(summary = "新增roleMenu", description = "新增roleMenu")
    @PostMapping
    public R save(@RequestBody RoleMenu roleMenu) {
        return R.ok(roleMenuService.save(roleMenu));
    }

    /**
     * 修改roleMenu
     *
     * @param roleMenu roleMenu
     * @return R
     */
    @Operation(summary = "修改roleMenu", description = "修改roleMenu")
    @PutMapping
    public R updateById(@RequestBody RoleMenu roleMenu) {
        return R.ok(roleMenuService.updateById(roleMenu));
    }


}

