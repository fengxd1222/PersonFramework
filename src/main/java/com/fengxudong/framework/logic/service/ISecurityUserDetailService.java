package com.fengxudong.framework.logic.service;

import com.fengxudong.framework.base.service.IFrameworkService;
import com.fengxudong.framework.security.detail.SecurityUserDetail;

public interface ISecurityUserDetailService extends IFrameworkService<SecurityUserDetail> {
    SecurityUserDetail getByName(String name);
}
