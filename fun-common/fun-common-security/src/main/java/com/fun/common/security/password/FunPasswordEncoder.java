package com.fun.common.security.password;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

/**
 * @program: fun-project
 * @description: BCryptPasswordEncoder 子类, 用于处理 明文密码与加密密码
 * @author: WhyWhatHow
 * @create: 2022-03-07 13:50
 **/
@Slf4j
public class FunPasswordEncoder extends BCryptPasswordEncoder {


    static Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    /**
     * 情景: db中有大量明文密码的用户, 新入库用户采用 BcryptPasswordEncoder 加密
     * 目的:替换 明文密码用户密码转化-> Bcrypt编码方式*
     *
     * @param rawPassword     用户输入密码
     * @param encodedPassword 数据库密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

//        log.warn("[Fun-password Encoder->matches]-> raw: {},encoded:{}", rawPassword, encodedPassword);

        if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
            // 校验是否为明文密码
            if (rawPassword.toString().equals(encodedPassword)) {
                return true;
            }
        }
        //         处理 bcrypt 加密
        return super.matches(rawPassword, encodedPassword);
    }
/**
 * TODO [whywhathow] [2022/3/7] [must] 如果我们不仅想要兼容，还想将不安全的旧密码无缝修改成BCrypt密文，该如何操作呢？
 * 思路: [增量更新] -> user login 成功后, -> 将password 更换-> 刷新数据库一次mysql校验(在登录过程中)  -次读,一次写.
 * others: 提醒用户密码过弱,修改.
 */
}
