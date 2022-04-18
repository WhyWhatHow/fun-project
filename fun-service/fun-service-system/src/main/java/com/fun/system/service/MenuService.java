package com.fun.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.system.api.entity.Menu;

import java.util.List;
import java.util.Set;

/**
 * @author whywhathow
 * @since 2022-04-08 21:53:14
 */
public interface MenuService extends IService<Menu> {

    Menu getById(Integer menuId);

    /**
     * 根据roleId 查询对应的menuIds
     * @param roleId roleId
     * @return menuIds
     */
    List selectByRoleId(Long roleId);

    /**
     * 查询menu ,返回parentId对应的menus
     * @param parentId 父类id
     * @return menu
     */
    List menuTree(Integer parentId);

    /**
     * 假删除, update 操作 修改 menu_id ,以及parent_id= menuId 数据行
     * @param menuId menuId
     * @return  true | false
     */
    Boolean removeByMenuId(Integer menuId);

    /**
     *  通过roleId 获取所有的menu信息
     * @param roleId roleId
     * @return menus
     */
    Set<Menu> listByRoleId(Long roleId);
}

