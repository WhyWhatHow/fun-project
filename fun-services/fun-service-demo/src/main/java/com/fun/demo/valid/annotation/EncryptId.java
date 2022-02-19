package com.fun.demo.valid.annotation;

import com.fun.demo.valid.EncryptIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 * spring validation 自定义参数校验 ,只能针对单个元素, 不可以对多个元素进行校验,
 * 单元素校验:  最好走 {@link Pattern}  正则表达式方案,
 * 多个元素混合校验: 1. 自定义, 2, 分组
 * @author whywhathow
 * {@link NotNull}
 */
@Documented
@Target({ElementType.TYPE, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EncryptIdValidator.class)// 校验规则
public @interface EncryptId {
    String message() default "加密id格式失败";
    // 分组信息
    Class<?>[] groups() default { };
    // 负载
    Class<? extends Payload>[] payload() default { };

    Class<?> handler();
}
