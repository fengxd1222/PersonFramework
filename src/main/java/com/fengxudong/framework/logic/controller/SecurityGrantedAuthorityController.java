package com.fengxudong.framework.logic.controller;

import com.fengxudong.framework.base.controller.FrameworkController;
import com.fengxudong.framework.response.FrameworkResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/granted")
public class SecurityGrantedAuthorityController extends FrameworkController {

    @PostMapping("/list")
    public FrameworkResult list(){
        return FrameworkResult.success();
    }
}
