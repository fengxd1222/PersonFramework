package com.fengxudong.framework.context;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Supplier;

public class FrameworkContextHolder {

    private static ThreadLocal<FrameworkContext> contextThreadLocal = new ThreadLocal<>();

    private static final String ORG_KEY = "orgId";

    public static FrameworkContext getFrameworkContext(){
        return contextThreadLocal.get();
    }

    public static void setFrameworkContext(@NotNull FrameworkContext context){
        clearFrameworkContext();
        contextThreadLocal.set(context);
    }

    public static void clearFrameworkContext(){
        contextThreadLocal.remove();
    }

    public static <T> T withOrgExecute(String orgId, Supplier<T> callable) {
        FrameworkContext frameworkContext = getFrameworkContext();

        if(StringUtils.isEmpty(orgId) &&( frameworkContext == null || StringUtils.isEmpty(frameworkContext.getOrgId()))){
            throw new FrameworkContextException("租户隔离模式下，租户id不能为空");
        }

        if(StringUtils.isEmpty(orgId)){
            orgId = frameworkContext.getOrgId();
        }

        try{
            setFrameworkContext(new FrameworkContext(orgId));
            return callable.get();
        }finally {
            setFrameworkContext(frameworkContext);
        }
    }

    public static <T> T withFrameworkContextExecute(FrameworkContext context,Supplier<T> callable){
        FrameworkContext frameworkContext = getFrameworkContext();

        try {
            setFrameworkContext(context);
            return callable.get();
        }finally {
            setFrameworkContext(frameworkContext);
        }
    }

    public static Optional<Long> getCurrentUserId(){
        FrameworkContext frameworkContext = getFrameworkContext();
        if(frameworkContext!=null){
            return Optional.ofNullable(frameworkContext.getUserId());
        }
        return Optional.empty();
    }

    public static Optional<UserInfo> getCurrentUserInfo(){
        FrameworkContext frameworkContext = getFrameworkContext();
        if(frameworkContext!=null){
            return Optional.ofNullable(frameworkContext.getUserInfo());
        }
        return Optional.empty();
    }

    public static Optional<String> getCurrentOrgId() {
        FrameworkContext frameworkContext = getFrameworkContext();
        if(frameworkContext!=null && frameworkContext.getIsolation() && frameworkContext.getOrgId()==null){
            throw new FrameworkContextException("租户隔离模式下，租户id不能为空");
        }
        return Optional.ofNullable(frameworkContext.getOrgId());
    }

    public static Optional<String> getCurrentToken(){
        FrameworkContext frameworkContext = getFrameworkContext();
        if(frameworkContext!=null){
            return Optional.ofNullable(frameworkContext.getToken());
        }
        return Optional.empty();
    }

    public static boolean isolation(){
        FrameworkContext frameworkContext = getFrameworkContext();
        if(frameworkContext == null){
            return false;
        }
        return frameworkContext.getIsolation();
    }


}
