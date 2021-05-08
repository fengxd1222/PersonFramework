package com.fengxudong.framework.security.service;

import com.fengxudong.framework.security.detail.SecurityUserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {

    SecurityUserDetail getByName(String name);

}
