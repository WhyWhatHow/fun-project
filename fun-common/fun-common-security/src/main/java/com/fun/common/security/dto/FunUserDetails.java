package com.fun.common.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * @program: fun-project
 * @description: funUserDetails -> login oauth2认证
 * @author: WhyWhatHow
 * @create: 2022-04-28 14:43
 **/
public class FunUserDetails extends User {


    /**
     * 用户id
     */
    private Long id;
    /**
     * 手机号
     */
    private String phone;

    /**
     * email
     */
    private String email;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    /**
     * roleIds
     */
    private List<Long> roleIds;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FunUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                          Long userId, String email, String phone, List<Long> rolesId) {
        super(username, password, authorities);
        this.email = email;
        this.id = id;
        this.phone = this.phone;
        this.roleIds = rolesId;
    }

    public FunUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id, String phone) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.phone = phone;
    }


    public FunUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
