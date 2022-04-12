package com.fun.common.core.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-02-21 16:16
 **/
@Data
public class BaseEntity implements Serializable {
    /**
     * 创建时间
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private Date createTime;
    /**
     * 更新时间, insert and update 时自动更新
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    private Date updateTime ;
    /**
     * 状态位
     */
    @Schema(description = "状态位")
    private Integer status = 0;
    /**
     * 删除标记, 0, 表示未删除, 1==删除
     */
    @TableLogic
    @Schema(description = "删除标记位")
    private Integer delFlag = 0;
}
