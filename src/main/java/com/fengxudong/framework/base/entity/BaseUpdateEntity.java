package com.fengxudong.framework.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;

public class BaseUpdateEntity extends BaseCreateEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "更新时间", hidden = false, example = "0")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
    @ApiModelProperty(value = "更新人员ID", hidden = true)
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;
    @ApiModelProperty(value = "更新人", hidden = false)
    @TableField(value = "update_user_name", fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;
    /**
     * 是否已经删除//逻辑删除
     */
    @ApiModelProperty(value = "逻辑删除标志", hidden = true)
    @TableField(value = "is_deleted", fill = FieldFill.INSERT, select = false)
    @TableLogic
    private Integer isDeleted = 0;

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }


}
