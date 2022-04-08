package com.fun.system.api.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Menu)表实体类
 *
 * @author whywhathow
 * @since 2022-04-08 21:37:30
 */
@EqualsAndHashCode
@Data
@TableName("sys_menu")
@Schema(description = "Menu实体类")
@SuppressWarnings("serial")
public class Menu extends BaseEntity {

    /**
     * 权限id
     */
    @TableId
    @Schema(description = "权限id")
    private Integer menuId;
    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String name;
    /**
     * 菜单权限标识
     */
    @Schema(description = "菜单权限标识")
    private String permission;
    /**
     * 前端uri
     */
    @Schema(description = "前端uri")
    private String path;
    /**
     * 父类menu_id, -1 标记为根节点
     */
    @Schema(description = "父类menu_id, -1 标记为根节点")
    private Integer parentId;
    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;
    /**
     * 排序值
     */
    @Schema(description = "排序值")
    private Integer sortOrder;
    /**
     * 0 开启, 1 关闭
     */
    @Schema(description = "0 开启, 1 关闭")
    private String keepAlive;
    /**
     * 菜单类型,0 菜单, 1 按钮
     */
    @Schema(description = "菜单类型,0 菜单, 1 按钮")
    private String type;
}
