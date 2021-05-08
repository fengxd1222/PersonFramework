package com.fengxudong.framework.security.filter;

import com.fengxudong.framework.cache.IFrameworkCache;
import com.fengxudong.framework.context.FrameworkContext;
import com.fengxudong.framework.context.FrameworkContextHolder;
import com.fengxudong.framework.context.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TokenFilter extends BasicAuthenticationFilter {
    private IFrameworkCache frameworkCache;
    public TokenFilter(AuthenticationManager authenticationManager, IFrameworkCache frameworkCache) {
        super(authenticationManager);
        this.frameworkCache = frameworkCache;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            chain.doFilter(request,response);
            return;
        }
        UserInfo userInfo = (UserInfo) frameworkCache.get(token);
        if(userInfo==null){
            chain.doFilter(request,response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo.getUsername(), null, new ArrayList<>()));
        FrameworkContextHolder.setFrameworkContext(new FrameworkContext(userInfo,token));
        super.doFilterInternal(request, response, chain);
    }
}
