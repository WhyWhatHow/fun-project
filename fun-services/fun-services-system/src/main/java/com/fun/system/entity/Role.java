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
 * (Role)表实体类
 * 
 * @author whywhathow
 * @since 2022-03-15 13:08:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "Role实体类")
@SuppressWarnings("serial")
public class Role extends  BaseEntity {
    
            /**
        * 角色id
        */
                        @TableId
                @ApiModelProperty("角色id")
        private Long roleId;
                 /**
        * 角色名
        */
                        @ApiModelProperty("角色名")
        private String roleName;
                 /**
        * $column.comment
        */
                        @ApiModelProperty("$column.comment")
        private String roleCode;
                 }
