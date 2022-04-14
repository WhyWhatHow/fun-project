package com.fun.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fun.common.core.domain.R;
import com.fun.common.core.domain.RCode;
import com.fun.common.web.exception.ServiceException;
import com.fun.system.api.entity.Role;
import com.fun.system.api.vo.RoleVo;
import com.fun.system.service.RoleMenuService;
import com.fun.system.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**done
 * @author whywhathow
 * @since 2022-04-08 21:53:16
 */
@Tag(name = "Role", description = "角色管理模块")
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("role")
public class RoleController {
    @Resource
    private final RoleService roleService;

    @Resource
    private final RoleMenuService roleMenuService;

    /**
     * 分页查询
     * done
     * @param page 分页对象
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page")
    public R Page(Page page) {
        return R.ok(roleService.page(page, Wrappers.emptyWrapper()));
    }


    /**
     * 通过id查询role
     * done
     * @param roleId id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{roleId}")
    public R getById(@PathVariable("roleId")@NotBlank Long roleId) {
        return R.ok(roleService.getById(roleId));
    }

    /**
     * 通过id删除role ,以及 sys_role_menu
     * done
     * @param roleId id
     * @return R
     */
    @Operation(summary = "通过id删除role", description = "通过id删除role")
    @DeleteMapping("/{roleId}")
    public R removeById(@PathVariable @NotBlank Long roleId) {
        return R.ok(roleService.removeById(roleId));
    }

    /**
     * 新增role
     * done
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
     * done
     * @param role role
     * @return R
     */
    @Operation(summary = "修改role", description = "修改role")
    @PutMapping
    public R updateById(@RequestBody Role role) {
        return R.ok(roleService.updateById(role));
    }

    /**
     * 为角色添加权限
     *  done
     * @param roleVo role
     * @return true or false
     */
    @Operation(summary = "修改role", description = "修改role")
    @PutMapping("/menu")
    public R saveRoleMenus(@RequestBody @Validated RoleVo roleVo) {
        Role role = roleService.getById(roleVo.getRoleId());
        if (ObjectUtils.isEmpty(role)) {
            log.error("[update-role-menu]-Error: role 不存在");
            throw new ServiceException(RCode.ROLE_NOT_EXIST);
        }
        return R.ok(roleMenuService.saveRoleMenus(roleVo));
    }
}

