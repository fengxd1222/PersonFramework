package com.fengxudong.framework.security.service.impl;

import com.fengxudong.framework.logic.service.ISecurityUserDetailService;
import com.fengxudong.framework.security.detail.SecurityUserDetail;
import com.fengxudong.framework.security.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Autowired
    ISecurityUserDetailService securityUserDetailService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.securityUserDetailService.getByName(s);
    }

    @Override
    public SecurityUserDetail getByName(String name) {
        return this.securityUserDetailService.getByName(name);
    }
}
