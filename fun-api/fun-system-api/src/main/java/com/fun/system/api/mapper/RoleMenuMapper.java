package com.fun.system.api.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.system.api.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * RoleMenuMapper DAO层
 *
 * @author whywhathow
 * @since 2022-04-08 21:38:14
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RoleMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RoleMenu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RoleMenu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RoleMenu> entities);


    Boolean delByRoleId(@Param("roleId") Long roleId);
}

