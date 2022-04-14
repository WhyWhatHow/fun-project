package com.fun.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.system.api.entity.RoleMenu;
import com.fun.system.api.vo.RoleVo;

/**
 * (RoleMenu)表服务接口
 *
 * @author whywhathow
 * @since 2022-04-14 17:05:09
 */
public interface RoleMenuService extends IService<RoleMenu> {
    /**
     *  为role 赋予menu权限, 即写sys_role_menu表
     * @param roleVo role_vo
     * @return true | false
     */
    Boolean saveRoleMenus(RoleVo roleVo);


    Boolean removeByRoleId(Long roleId);
}

