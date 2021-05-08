package com.fengxudong.framework.logic.controller;

import com.fengxudong.framework.base.controller.FrameworkController;
import com.fengxudong.framework.context.FrameworkContextHolder;
import com.fengxudong.framework.logic.service.ISecurityUserDetailService;
import com.fengxudong.framework.response.FrameworkResCodeEnum;
import com.fengxudong.framework.response.FrameworkResult;
import com.fengxudong.framework.security.detail.SecurityUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends FrameworkController {

    @Autowired
    private ISecurityUserDetailService securityUserDetailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/register")
    public FrameworkResult register(@RequestBody SecurityUserDetail securityUserDetail){
        return FrameworkContextHolder.withOrgExecute(securityUserDetail.getOrgId(),()->{
            SecurityUserDetail byName = securityUserDetailService.getByName(securityUserDetail.getUsername());
            if(byName!=null){
                return FrameworkResult.failure(FrameworkResCodeEnum.USER_REPEAT);
            }

            securityUserDetail.setPassword(bCryptPasswordEncoder.encode(securityUserDetail.getPassword()));
            securityUserDetailService.save(securityUserDetail);
            return FrameworkResult.success(securityUserDetail);
        });
    }
}
