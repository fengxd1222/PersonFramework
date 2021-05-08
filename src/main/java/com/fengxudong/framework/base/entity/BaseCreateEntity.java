package com.fengxudong.framework.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

public class BaseCreateEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", hidden = false, example = "0")
    private Long createTime;

    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人ID", hidden = true)
    private String createUserId;

    @ApiModelProperty(value = "创建人", hidden = false)
    @TableField(value = "create_user_name", fill = FieldFill.INSERT)
    private String createUserName;

    @ApiModelProperty(value = "组织ID", hidden = true)
    @TableField(value = "org_id", updateStrategy = FieldStrategy.NOT_EMPTY)
    private String orgId;


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Override
    public String toString() {
        return "BaseCreateEntity [createTime=" + createTime + ", createUserId=" + createUserId + ", orgId=" + orgId
                + "]";
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
