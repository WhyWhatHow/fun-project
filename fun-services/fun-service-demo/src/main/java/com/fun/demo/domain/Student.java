package com.fun.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-02-17 22:10
 **/
@Data
@Accessors(chain = true)
public class Student {
    @NotNull(message = "name 不能为空")
    @Length(min = 2, max = 10 ,message = " name 长度应在2-10之间")
    String name;
    @NotNull(message = "age 不能为空")
    @Min(1) @Max(90)
    Integer age;
    @NotNull(message = "email 不能为空 ")
    @Email(message = "email 格式不正确")
    String email;


}
