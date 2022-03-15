package com.fun.system.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (UserRole)表实体类
 * 
 * @author whywhathow
 * @since 2022-03-15 13:08:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "UserRole实体类")
@SuppressWarnings("serial")
public class UserRole extends  BaseEntity {
    
            /**
        * $column.comment
        */
                        @TableId
                @ApiModelProperty("$column.comment")
        private Long userId;
                 /**
        * $column.comment
        */
                        @ApiModelProperty("$column.comment")
        private Long roleId;
     }
