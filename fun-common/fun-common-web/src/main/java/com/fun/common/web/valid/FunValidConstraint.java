package com.fun.common.web.valid;

import com.fun.common.web.utils.ApplicationContextUtils;
import com.fun.common.web.valid.annotation.FunValid;
import com.fun.common.web.valid.handler.FunValidHandler;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * @program: fun-project
 * @description: FunValid 的约束条件
 * @author: WhyWhatHow
 * @create: 2022-02-19 21:22
 **/
public class FunValidConstraint implements ConstraintValidator<FunValid, Object> {
    private FunValid funValid;

    @Override
    public void initialize(FunValid valid) {
        this.funValid = valid;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 1. 校验逻辑
        if (value != null) {
            FunValidHandler handler = ApplicationContextUtils.getBean(funValid.handler());
            return  Optional.ofNullable(handler).
                    map(obj->{
                      return  handler.valid(value,funValid);
                    }).
                    orElse( false);

        }
        //2.交给@NotNull处理
        return true;
    }
}
