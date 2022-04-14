package com.fun.system.api.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class RoleMenu  {

    /**
     * roleId
     */
    @TableId
    @Schema(description = "roleId")
    private Long roleId;
    /**
     * menuId
     */
    @Schema(description = "menuId")
    private Long menuId;
}
