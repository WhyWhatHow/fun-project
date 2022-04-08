package com.fun.system.api.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Role)表实体类
 *
 * @author whywhathow
 * @since 2022-04-08 21:37:30
 */
@EqualsAndHashCode
@Data
@TableName("sys_role")
@Schema(description = "Role实体类")
@SuppressWarnings("serial")
public class Role extends BaseEntity {

    /**
     * 角色id
     */
    @TableId
    @Schema(description = "角色id")
    private Long roleId;
    /**
     * 角色名
     */
    @Schema(description = "角色名")
    private String roleName;
    /**
     * code
     */
    @Schema(description = "code")
    private String roleCode;
}
