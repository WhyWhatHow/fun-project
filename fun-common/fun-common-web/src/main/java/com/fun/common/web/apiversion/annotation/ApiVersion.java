package com.fun.common.web.apiversion.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {
    /**
     * 版本号
     *
     * @return 返回版本号, 按照[a,b)返回,
     */
    double value() default 1.0;
}
