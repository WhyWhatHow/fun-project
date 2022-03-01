package com.fun.common.web.valid.annotation;

import com.fun.common.web.valid.FunValidConstraint;
import com.fun.common.web.valid.handler.FunValidHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义数据校验注解, 可以用在 class, method
 * JSR303
 * @author whywhathow
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FunValidConstraint.class}) // 数据校验的实体类
public @interface FunValid {
    @NotNull
    String message() default "参数校验失败";

    /**
     * 分组校验
     */
    Class<?>[] groups() default {};

    /**
     * 负载
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * FunValid 的 validator 数据校验类,
     * Eg: @FunValid(handler = XXXHandler.class), 就会按照XXXHandler定义的规则进行参数校验
     * Ps: 如果想要进行多数据间校验, 将@FunValid注解放于类中.
     */
    Class<? extends FunValidHandler> handler() ;
}
