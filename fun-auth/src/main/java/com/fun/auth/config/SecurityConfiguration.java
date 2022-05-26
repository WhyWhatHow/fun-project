package com.fun.auth.config;

import com.fun.auth.password.FunPasswordEncoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @program: fun-project
 * @description: Web security配置类, 配置放行的request, username,password 采用 form表单登录方式
 * @author: WhyWhatHow
 * @create: 2022-03-16 22:33
 **/
@Configuration
@Slf4j
@Order(80)
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
//        DONE [whywhathow] [26/5/2022] [must] change passwordEncoder
        return new FunPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 注入 dao userDetailsService 认证方案.
     *
     * @param builder the {@link AuthenticationManagerBuilder} to use
     * @throws Exception
     */
    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder builder) {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
//                .passwordEncoder()
        ;
    }

    /**
     * 通过父类 注册 AuthenticationManager
     *
     * @return
     */
    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
//    ISSUE [whywhathow] [24/5/2022] [must]  这个会导致 stackoverflow error
        //        return super.authenticationManager();
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//                 表单登录
//                .formLogin()
//                .and()
//                .logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
//                .and()
                .authorizeRequests()
                //   放行 /actuator 请求
                .antMatchers(
                        "/actuator"
                        , "/oauth/**"
                        , "/test/**"
                ).permitAll()
//                .antMatchers("/oauth/**").anonymous()
//                 其他请求, 需要认证
                .anyRequest().authenticated()
                .and()
//                .cors().disable()
                .csrf().disable()

        ;
//        http.authorizeRequests().antMatchers("/**").permitAll();
//        ;
//        http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated() // 拦截所有请求, 需要用户认证信息
//                .and().csrf().disable();
//        http.formLogin()
////                .loginPage("/token/login").loginProcessingUrl("/token/form")
////                .failureHandler()
//                .and()
//                .logout()
////                .logoutSuccessHandler().deleteCookies("JSESSIONID").invalidateHttpSession(true)
//                .and()
//                .authorizeRequests().antMatchers("/token/**","/actuator/**","/mobile/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
    }


}
