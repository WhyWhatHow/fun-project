package com.fun.services.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @program: fun-project
 * @description: SpringSecurity 标准配置入口
 * 主要用来配置 PasswordEncoder、默认的用户（user/user和admin/admin）和HttpSecurity安全
 * 规则。
 * @author: WhyWhatHow
 * @create: 2022-03-13 10:01
 **/
@Profile("simple")
@Slf4j
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置用户 信息, 可以配置 jdbc 连接模式
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()

                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .and()
                .withUser("test").password(passwordEncoder().encode("test")).roles("USER")
        ;
    }
//    授权码模式:authorization_code密码模式password客户端模式client_credentials隐式授权模式implicit
    /**
     * 端点配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.warn("[SecurityConfiguratiion ] http 执行");
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")

                .and()
                .formLogin();
    }
    @Override
    public  void configure(WebSecurity web) throws Exception{
        super.configure(web);
        web.ignoring().antMatchers("/favicon.ico");
    }



}
