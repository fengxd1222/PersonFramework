package com.fengxudong.framework.logic.service.impl;

import com.fengxudong.framework.base.service.impl.FrameworkServiceImpl;
import com.fengxudong.framework.logic.mapper.SecurityUserDetailMapper;
import com.fengxudong.framework.logic.service.ISecurityUserDetailService;
import com.fengxudong.framework.security.detail.SecurityUserDetail;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailServiceImpl extends FrameworkServiceImpl<SecurityUserDetailMapper, SecurityUserDetail> implements ISecurityUserDetailService {

    @Override
    public SecurityUserDetail getByName(String name) {
        return frameworkBasemapper.selectByName(name);
    }
}
