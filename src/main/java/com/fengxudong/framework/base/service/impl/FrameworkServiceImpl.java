package com.fengxudong.framework.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengxudong.framework.base.mapper.FrameworkBaseMapper;
import com.fengxudong.framework.base.service.IFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;

public class FrameworkServiceImpl<M extends FrameworkBaseMapper<T>, T> extends ServiceImpl<M,T> implements IFrameworkService<T> {
    @Autowired
    protected M frameworkBasemapper;
    public M getFrameworkBasemapper() {
        return this.frameworkBasemapper;
    }

    public FrameworkServiceImpl() {

    }
    @Override
    public T selectIdWithoutLogicDeleted(Long id) {
        return frameworkBasemapper.selectIdWithoutLogicDeleted(id);
    }
}
