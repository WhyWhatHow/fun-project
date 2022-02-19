package com.fun.demo.valid.handler;

import com.fun.common.web.valid.annotation.FunValid;
import com.fun.common.web.valid.handler.FunValidHandler;
import com.fun.demo.domain.Student;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @program: fun-project
 * @description: 年龄生日校验器
 * @author: WhyWhatHow
 * @create: 2022-02-19 22:57
 **/
@Component
public class BirthdayAndAgeValidHandler implements FunValidHandler<Student> {

    @Override
    public boolean valid(Student stu, FunValid funValid) {
        Integer age = stu.getAge();
        Date birthday = stu.getBirthday();
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int nowYear = calendar.get(Calendar.YEAR);
        calendar.setTime(birthday);
        int birhYear = calendar.get(Calendar.YEAR);
        if (age == nowYear - birhYear) {
            return true;
        }
        return false;
    }
}
