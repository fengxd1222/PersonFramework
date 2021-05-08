package com.fengxudong.framework.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface FrameworkBaseMapper<T> extends BaseMapper<T> {

    T selectIdWithoutLogicDeleted(Long id);
}
