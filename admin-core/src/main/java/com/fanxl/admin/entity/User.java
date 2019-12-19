package com.fanxl.admin.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.social.security.SocialUserDetails;

import javax.persistence.Table;
import java.util.Collection;
import java.util.Date;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:19
 */
@Table(name = "admin_user")
@Data
public class User extends StringIdEntity implements SocialUserDetails {

    private String username;

    private String password;

    private String phone;

    private String salt;

    private String head;

    private String email;

    private Date lastLoginTime;

    private Date accountExpired;

    private String newPassword;

    private String oldPassword;

    @Override
    public String getUserId() {
        return getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
