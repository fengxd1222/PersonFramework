package com.fengxudong.framework.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengxudong.framework.cache.IFrameworkCache;
import com.fengxudong.framework.context.FrameworkContext;
import com.fengxudong.framework.context.FrameworkContextHolder;
import com.fengxudong.framework.context.UserInfo;
import com.fengxudong.framework.response.FrameworkResCodeEnum;
import com.fengxudong.framework.response.FrameworkResult;
import com.fengxudong.framework.security.detail.SecurityUserDetail;
import com.fengxudong.framework.security.service.UserDetailService;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.Header;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private UserDetailService userDetailService;

    private IFrameworkCache frameworkCache;

    public LoginFilter(AuthenticationManager authenticationManager, UserDetailService userDetailService, IFrameworkCache frameworkCache) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.frameworkCache = frameworkCache;
        super.setFilterProcessesUrl("/user/login");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        SecurityUserDetail securityUserDetail = objectMapper.readValue(request.getInputStream(), SecurityUserDetail.class);
        FrameworkContextHolder.setFrameworkContext(new FrameworkContext(securityUserDetail.getOrgId(), null, true, null));
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(securityUserDetail.getUsername(), securityUserDetail.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserInfo userInfo = new UserInfo();
        SecurityUserDetail securityUserDetail = userDetailService.getByName(authResult.getName());
        userInfo.setUserId(securityUserDetail.getId());
        userInfo.setOrgId(securityUserDetail.getOrgId());
        userInfo.setUsername(authResult.getName());

        String token = UUID.randomUUID().toString();
        //token不应该被租户隔离，需要单独创建上下文
        FrameworkContext frameworkContext = new FrameworkContext(false);
        FrameworkContextHolder.withFrameworkContextExecute(frameworkContext,()->{
            frameworkCache.set(token, userInfo, 1, TimeUnit.MINUTES);
            return null;
        });

//        FrameworkContextHolder.setFrameworkContext(new FrameworkContext(userInfo, token));

        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        writer.write(new ObjectMapper().writeValueAsString(FrameworkResult.success(token)));
        writer.flush();
        writer.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        FrameworkContextHolder.clearFrameworkContext();
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(FrameworkResult.failure(FrameworkResCodeEnum.PASSWORD_INCORRECT)));
        writer.flush();
        writer.close();
    }
}
