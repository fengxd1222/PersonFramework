package com.fengxudong.framework.logic.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.fengxudong.framework.base.mapper.FrameworkBaseMapper;
import com.fengxudong.framework.security.detail.SecurityUserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SecurityUserDetailMapper extends FrameworkBaseMapper<SecurityUserDetail> {

    SecurityUserDetail selectByName(@Param("username") String username);
}
