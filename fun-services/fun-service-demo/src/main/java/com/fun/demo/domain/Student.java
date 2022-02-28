package com.fun.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fun.common.core.domain.BaseEntity;
import com.fun.common.web.valid.annotation.FunValid;
import com.fun.demo.valid.annotation.BirthAndAgeValid;
import com.fun.demo.valid.handler.BirthdayAndAgeValidHandler;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-02-17 22:10
 **/
@Data
@ToString(callSuper = true)
@FunValid(handler = BirthdayAndAgeValidHandler.class,message = "生日与日期不匹配")
@Accessors(chain = true)
@BirthAndAgeValid
public class Student extends BaseEntity {
    @NotNull(message = "name 不能为空")
    @Length(min = 2, max = 10, message = " name 长度应在2-10之间")
    String name;
    @NotNull(message = "age 不能为空")
    @Min(1) @Max(90)
    Integer age;
    @NotNull(message = "email 不能为空 ")
    @Email(message = "email 格式不正确")
    String email;
    @NotNull(message = "birthday 不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    Date birthday;


}
