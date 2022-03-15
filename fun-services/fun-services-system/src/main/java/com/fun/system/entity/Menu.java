package com.fun.system.entity;


import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Menu)表实体类
 * 
 * @author whywhathow
 * @since 2022-03-15 13:08:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "Menu实体类")
@SuppressWarnings("serial")
public class Menu extends  BaseEntity {
    
            /**
        * 权限id
        */
                        @TableId
                @ApiModelProperty("权限id")
        private Integer menuId;
                 /**
        * 权限名称
        */
                        @ApiModelProperty("权限名称")
        private String name;
                 /**
        * 菜单权限标识
        */
                        @ApiModelProperty("菜单权限标识")
        private String permission;
                 /**
        * 前端uri
        */
                        @ApiModelProperty("前端uri")
        private String path;
                 /**
        * 父类menu_id, -1 标记为根节点
        */
                        @ApiModelProperty("父类menu_id, -1 标记为根节点")
        private Integer parentId;
                 /**
        * 图标
        */
                        @ApiModelProperty("图标")
        private String icon;
                 /**
        * 排序值
        */
                        @ApiModelProperty("排序值")
        private Integer sortOrder;
                 /**
        * 0 开启, 1 关闭
        */
                        @ApiModelProperty("0 开启, 1 关闭")
        private String keepAlive;
                 /**
        * 菜单类型,0 菜单, 1 按钮
        */
                        @ApiModelProperty("菜单类型,0 菜单, 1 按钮")
        private String type;
                         /**
        * 状态位
        */
                        @ApiModelProperty("状态位")
        private Integer status;
         }
