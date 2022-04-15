package com.fun.system.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

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
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "角色id")
    private Long roleId;
    /**
     * 角色名
     */
    @NotBlank
    @Schema(description = "角色名")
    private String roleName;
    /**
     * code
     */
    @NotBlank
    @Schema(description = "code")
    private String roleCode;
}
