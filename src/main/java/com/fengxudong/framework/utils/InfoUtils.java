package com.fengxudong.framework.utils;

import com.fengxudong.framework.context.FrameworkContextHolder;
import com.fengxudong.framework.context.UserInfo;

import java.util.Optional;

public class InfoUtils {

    public static String currentOrgId(){
        Optional<String> optional = FrameworkContextHolder.getCurrentOrgId();
        return optional.orElse(null);
    }

    public static Long currentUserId(){
        return FrameworkContextHolder.getCurrentUserId().orElse(null);
    }

    public static UserInfo currentUserInfo(){
        return FrameworkContextHolder.getCurrentUserInfo().orElse(new UserInfo());
    }
}
