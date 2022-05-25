package com.fun.auth.dto;

import org.springframework.security.core.GrantedAuthority;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-05-24 16:15
 **/
public class FunGrantedAuthority implements GrantedAuthority {
    String val;

    public FunGrantedAuthority() {
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public FunGrantedAuthority(String val) {
        this.val = val;
    }

    @Override
    public String getAuthority() {
        return this.val;
    }
}
