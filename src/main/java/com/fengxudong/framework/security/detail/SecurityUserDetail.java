package com.fengxudong.framework.security.detail;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fengxudong.framework.base.entity.BaseUpdateEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@TableName(value = "f_user")
public class SecurityUserDetail extends BaseUpdateEntity implements UserDetails {

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "expired")
    private Long expired;

    @TableField(value = "is_lock")
    private Integer isLock;

    @TableField(value = "enabled")
    private Integer enabled;

    @TableField(value = "credentials_expired")
    private Long credentialsExpired;

    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    //可用的url集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return System.currentTimeMillis() <= this.expired;
    }

    //是否是 非锁定账户
    @Override
    public boolean isAccountNonLocked() {
        return this.isLock != 1;
    }

    //凭证是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return System.currentTimeMillis() <= this.credentialsExpired;
    }

    //是否启用
    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }

    public Long getExpired() {
        return expired;
    }

    public void setExpired(Long expired) {
        this.expired = expired;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

//    public Integer getEnabled() {
//        return enabled;
//    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Long getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Long credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
}
