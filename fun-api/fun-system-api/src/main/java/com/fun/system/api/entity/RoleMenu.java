package com.fun.system.api.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (RoleMenu)表实体类
 *
 * @author whywhathow
 * @since 2022-04-08 21:37:31
 */
@EqualsAndHashCode
@Data
@TableName("sys_role_menu")
@Schema(description = "RoleMenu实体类")
@SuppressWarnings("serial")
public class RoleMenu extends BaseEntity {

    /**
     * $column.comment
     */
    @TableId
    @Schema(description = "$column.comment")
    private Long roleId;
    /**
     * $column.comment
     */
    @Schema(description = "$column.comment")
    private Long menuId;
}
