package com.fengxudong.framework.base.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class FrameworkController {
    @Autowired
    HttpServletRequest request;

    protected <T> Page<T> getPage() {
        int index = 1;
        // 页数
        Integer pageNo = request.getParameter("pageNo")==null?1:Integer.parseInt(request.getParameter("pageNo"));
        // 分页大小
        Integer pageSize = request.getParameter("pageSize")==null?10:Integer.parseInt(request.getParameter("pageSize"));

        return new Page<T>(pageNo, pageSize);
    }
}
