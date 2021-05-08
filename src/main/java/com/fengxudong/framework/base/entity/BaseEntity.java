package com.fengxudong.framework.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //配置设置使用全局唯一UUID
    @ApiModelProperty(value = "主键ID", hidden = true)
    @TableId(type = IdType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
