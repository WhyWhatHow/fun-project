package com.fun.demo.valid.validator;

import com.fun.demo.domain.Student;
import com.fun.demo.valid.annotation.BirthAndAgeValid;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: fun-project
 * @description: 生日和年龄的校验规则
 * ConstraintValidator<EncryptId, String> , 第一个字段: 注解类型, 第二个字段: 注解修饰的字段
 * @author: WhyWhatHow
 * @create: 2022-02-19 16:26
 **/
@Slf4j
public class BirthAndAgeValidator implements ConstraintValidator<BirthAndAgeValid, Student> {
    private BirthAndAgeValid valid;

    @Override
    public void initialize(BirthAndAgeValid valid) {
        this.valid = valid;
    }

    @Override
    public boolean isValid(Student value, ConstraintValidatorContext context) {
        // birthday And Age 匹配校验
        if (value != null) {
            Date birthday = value.getBirthday();
            Integer age = value.getAge();
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthday);
            int birthYear = cal.get(Calendar.YEAR);
            cal.setTime(new Date());
            int nowYear = cal.get(Calendar.YEAR);
            if (nowYear - birthYear == age) {
                return true;
            }
            return false;
        }
        // @NotNull 校验
        return true;
    }
}
