package com.fun.demo.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @program: fun-project
 * @description: springboot 参数校验测试 -- user 实体类
 * <p>参考链接https://segmentfault.com/a/1190000023471742#item-2-5</p>
 * @author: WhyWhatHow
 * @create: 2022-02-19 16:06
 **/
@Data
public class User {
    @Min(value = 10000000000000000L, groups = Update.class)
    private Long userId;

    @NotNull(groups = {Save.class, Update.class})
    @Length(min = 2, max = 10, groups = {Save.class, Update.class})
    private String userName;

    @NotNull(groups = {Save.class, Update.class})
    @Length(min = 6, max = 20, groups = {Save.class, Update.class})
    private String account;

    @NotNull(groups = {Save.class, Update.class})
    @Length(min = 6, max = 20, groups = {Save.class, Update.class})
    private String password;

    /**
     * group 分组依赖,根据 save.class, update.class 分组
     */
    public interface Save {
    }

    public interface Update {
    }
}
