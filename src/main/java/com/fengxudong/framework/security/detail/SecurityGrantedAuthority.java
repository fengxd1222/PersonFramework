package com.fengxudong.framework.security.detail;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fengxudong.framework.base.entity.BaseEntity;
import org.springframework.security.core.GrantedAuthority;

@TableName(value = "f_authority")
public class SecurityGrantedAuthority extends BaseEntity implements GrantedAuthority {

    @TableField(value = "authority")
    private String authority;

    @TableField(value = "description")
    private String description;


    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
