package com.fun.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.common.core.domain.R;
import com.fun.system.api.entity.Menu;
import com.fun.system.service.MenuService;
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
@Tag(name = "Menu", description = "权限管理模块")
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("menu")
public class MenuController {
    @Resource
    private final MenuService menuService;

    /**
     * 根据roleId 查询对应的menuIds
     * @param roleId roleId
     * @return
     */
    @Operation(description = "根据roleId查询对应的menuId")
    @GetMapping("/tree/{roleId}")
    public R selectByRoleId(@PathVariable("roleId") Long roleId) {
        return R.ok(menuService.selectByRoleId(roleId));
    }

    @Operation(description = "根据parentId 返回对应的menu列表")
    @GetMapping("/tree")
    public R menuTree( Integer parentId) {
        return R.ok(menuService.menuTree(parentId));
    }

    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R Page(Page page) {
        return R.ok(menuService.page(page, Wrappers.emptyWrapper()));
    }


    /**
     * 通过id查询menu 如果是parentId,则返回所有的menu信息
     *
     * @param menuId id
     * @return R
     */
    @Operation(summary = "通过menuId查询", description = "通过menuId查询")
    @GetMapping("/{menuId}")
    public R getById(@PathVariable("menuId") Integer menuId) {
        return R.ok(menuService.getById(menuId));
    }

    /**
     * 通过id删除menu
     *
     * @param menuId id
     * @return R
     */
    @Operation(summary = "通过id删除menu", description = "通过id删除menu")
    @DeleteMapping("/{menuId}")
    public R removeById(@PathVariable Integer menuId) {
        return R.ok(menuService.removeById(menuId));
    }

    /**
     * 新增menu
     *
     * @param menu menu
     * @return R
     */
    @Operation(summary = "新增menu", description = "新增menu")
    @PostMapping
    public R save(@RequestBody Menu menu) {
        return R.ok(menuService.save(menu));
    }

    /**
     * 修改menu
     *
     * @param menu menu
     * @return R
     */
    @Operation(summary = "修改menu", description = "修改menu")
    @PutMapping
    public R updateById(@RequestBody Menu menu) {
        return R.ok(menuService.updateById(menu));
    }


}

