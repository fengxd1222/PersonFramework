package com.fengxudong.framework.logic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fengxudong.framework.base.entity.BaseUpdateEntity;

@TableName(value = "f_user_authority")
public class UserAuthorityEntity extends BaseUpdateEntity {

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "authority_id")
    private Long authorityId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}
