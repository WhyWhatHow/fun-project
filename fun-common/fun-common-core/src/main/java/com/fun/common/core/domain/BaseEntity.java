package com.fun.common.core.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime = new Date();
    /**
     * 更新时间, insert and update 时自动更新
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime = new Date();
    /**
     * 状态位
     */
    private Integer stats = 0;
    /**
     * 删除标记, 0, 表示未删除, 1==删除
     */
    private Integer delFlag = 0;
}
