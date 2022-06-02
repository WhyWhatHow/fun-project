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
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * done
 *
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
     * done
     * 根据roleId 查询对应的menuIds
     *
     * @param roleId roleId
     * @return menuIds
     */
    @Operation(description = "根据roleId查询对应的menuId")
    @GetMapping("/tree/{roleId}")
    public R selectByRoleId(@PathVariable("roleId") Long roleId) {
        return R.ok(menuService.selectByRoleId(roleId));
    }


    /**
     * 根据parentId, 批量查询roleIds menu信息
     *
     * @param parentId
     * @param roles
     * @return
     */
    @Operation(description = "根据roleIds查询对应的menuId")
    @GetMapping()
    public R selectByRoleIds(Integer parentId, Long[] roles) {
        return R.ok(menuService.batchSelectByRoleIds(parentId, roles));
    }

    /**
     * done
     * TODO [whywhathow] [2022/4/14] [must] 缓存添加, 懒加载设置
     * 根据parentId 返回对饮的menuTree
     *
     * @param parentId parentId
     * @return menuTree
     */
    @Operation(description = "根据parentId 返回对应的menu列表")
    @GetMapping("/tree")
    public R menuTree(@Min(-1) Integer parentId) {
        return R.ok(menuService.menuTree(parentId));
    }

    /**
     * done
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
     * done
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
//
//    /**
//     * 获取  当前用户的menu
//     *
//     * @param parentId
//     * @return
//     */
//    @Operation(summary = "通过menuId查询", description = "通过menuId查询")
//    @GetMapping("")
//    public R<List<Tree<Long>>> getUserMenus(@NotNull Integer parentId) {
//        UserInfo user = SecurityUtils.getUser();
//        return R.ok(user.getPermissions());
//    }


    /**
     * 通过id删除menu ,update menuId =id || parentId=id 数据行
     *
     * @param menuId id
     * @return R
     */
    @Operation(summary = "通过id删除menu", description = "通过id删除menu")
    @DeleteMapping("/{menuId}")
    public R removeById(@PathVariable @NotNull Integer menuId) {
        return R.ok(menuService.removeByMenuId(menuId));
    }

    /**
     * 新增menu
     *
     * @param menu menu
     * @return R
     */
    @Operation(summary = "新增menu", description = "新增menu")
    @PostMapping
    public R save(@RequestBody @Valid Menu menu) {
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
    public R updateById(@RequestBody @Valid Menu menu) {
        return R.ok(menuService.updateById(menu));
    }

    /**
     * 获取当前用户的menus
     * 无法实现, 不想将roleIds 放在authentication中
     * @param parentId
     * @return
     */
//    @Deprecated
//    @GetMapping
//    @Operation(summary = "获取当前登录用户的menus", description = "获取当前登录用户的menus")
//    public R getUserMenuInfo(Integer parentId) {
////        TODO [whywhathow] [31/5/2022] [must] change , 不重要
//        List<Long> roles = SecurityUtils.getRoles();
//        if (CollectionUtils.isEmpty(roles)) {
//            return R.ok(Collections.emptyList());
//        }
//        if (ObjectUtils.isEmpty(parentId)) {
//            parentId = -1;
//        }
//        Integer finalParentId = parentId;
//        List<Menu> collect = roles.stream()
//                .map(roleId -> {
//                    return menuService.getUserMenusByRoleId(roleId, finalParentId);
//                })
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//        return R.ok(collect);
//    }
}

