package com.fun.demo.valid.annotation;

import com.fun.demo.valid.validator.BirthAndAgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BirthAndAgeValidator.class)
public @interface BirthAndAgeValid {

    String message() default "生日与年龄不匹配";

    /**
     * 分组信息
     */
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};


}
