package com.fun.demo.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学生表(Student)表实体类
 *
 * @author whywhathow
 * @since 2022-02-21 22:52:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "Student实体类")
@SuppressWarnings("serial")
public class Student extends BaseEntity {

    /**
     * 学生id
     */
    @TableId
    @ApiModelProperty("学生id")
    private Long sid;
    /**
     * 学生姓名
     */
    @ApiModelProperty("学生姓名")
    private String name;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Integer gender;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
}
