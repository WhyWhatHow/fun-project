package com.fun.demo.valid;

import com.fun.demo.valid.annotation.EncryptId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: fun-project
 * @description: 生日和年龄的校验规则
 * @author: WhyWhatHow
 * @create: 2022-02-19 16:26
 **/
public class EncryptIdValidator implements ConstraintValidator<EncryptId, String> {
    private static final Pattern PATTERN = Pattern.compile("^[a-f\\d]{32,256}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 1. 我的校验规则
        if (value != null) {
            System.out.println(value);
            Matcher matcher = PATTERN.matcher(value);
            return matcher.find();
        }
        return false;
    }
}
