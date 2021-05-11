package com.fengxudong.framework.logic.service.impl;

import com.fengxudong.framework.base.service.impl.FrameworkServiceImpl;
import com.fengxudong.framework.logic.mapper.SecurityGrantedAuthorityMapper;
import com.fengxudong.framework.logic.service.ISecurityGrantedAuthorityService;
import com.fengxudong.framework.security.detail.SecurityGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class SecurityGrantedAuthorityServiceImpl extends FrameworkServiceImpl<SecurityGrantedAuthorityMapper, SecurityGrantedAuthority> implements ISecurityGrantedAuthorityService {
}
