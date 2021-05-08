package com.fengxudong.framework.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrameworkBaseConfig {
    public static String[] PERMIT_MATCHES;

    public static String TOKEN_NAME;

    public static String FILTER_TABLES;

    @Value("${framework.permit.matcher}")
    public void setPermitMatches(String[] permitMatches) {
        PERMIT_MATCHES = permitMatches;
    }
    @Value("${framework.token.name}")
    public void setTokenName(String tokenName) {
        TOKEN_NAME = tokenName;
    }
    @Value("${framework.filter.tables}")
    public void setFilterTables(String filterTables) {
        FILTER_TABLES = filterTables;
    }
}
