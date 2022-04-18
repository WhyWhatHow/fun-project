package com.fun.common.core.qo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: fun-project
 * @description: 查询默认实体类
 * @author: WhyWhatHow
 * @create: 2022-03-19 22:25
 **/
@Data
public class BaseQo implements Serializable {

    /**
     *    状态位
     *
     */
    private Integer status = 0;

    /**
     *   删除标识
     *
     */
    private Integer delFlag = 0;
}
