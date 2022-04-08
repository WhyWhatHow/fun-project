package com.fun.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.system.api.entity.Menu;

import java.util.List;

/**
 * @author whywhathow
 * @since 2022-04-08 21:53:14
 */
public interface MenuService extends IService<Menu> {

    List<Menu> getById(Integer menuId);
}

