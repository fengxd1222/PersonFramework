package com.fengxudong.framework.security.config;

import com.fengxudong.framework.base.config.FrameworkBaseConfig;
import com.fengxudong.framework.cache.IFrameworkCache;
import com.fengxudong.framework.security.filter.LoginFilter;
import com.fengxudong.framework.security.filter.TokenFilter;
import com.fengxudong.framework.security.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailService userDetailService;
    @Autowired
    IFrameworkCache frameworkCache;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  //关闭csrf
                .authorizeRequests()
                // user不需要鉴权
                .antMatchers(FrameworkBaseConfig.PERMIT_MATCHES).permitAll()
                // 其他都需要
                .anyRequest().authenticated()
                .and()
                .addFilter(new LoginFilter(super.authenticationManager(),userDetailService,frameworkCache))
                .addFilter(new TokenFilter(super.authenticationManager(),frameworkCache))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
