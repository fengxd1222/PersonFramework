package com.fengxudong.framework.base.service;

import com.baomidou.mybatisplus.extension.service.IService;

public interface IFrameworkService<T> extends IService<T> {
    T selectIdWithoutLogicDeleted(Long id);
}
