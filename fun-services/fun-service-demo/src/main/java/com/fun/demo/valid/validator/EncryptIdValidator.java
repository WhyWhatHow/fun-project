package com.fun.demo.valid.validator;

import com.fun.demo.valid.annotation.EncryptId;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: fun-project
 * @description: id 加密校验
 * ConstraintValidator<EncryptId, String> , 第一个字段: 注解类型, 第二个字段: 注解修饰的字段
 * @author: WhyWhatHow
 * @create: 2022-02-19 16:26
 **/
@Slf4j
public class EncryptIdValidator implements ConstraintValidator<EncryptId, String> {
    private static final Pattern PATTERN = Pattern.compile("^[a-f\\d]{32,256}$");

    private EncryptId encryptId;

    @Override
    public void initialize(EncryptId encryptId) {
        this.encryptId = encryptId;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.warn(" 自定义注解生效 --> Encrypt");
        log.warn(value);
        // 1. 我的校验规则
        if (value != null) {

            System.out.println(context);
            System.out.println(value);
            Matcher matcher = PATTERN.matcher(value);
            return matcher.find();
        }
        return false;
    }
}
