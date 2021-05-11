package com.fengxudong.framework.init;

import java.lang.annotation.*;

/**
 * 权限注解，用于扫描
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoleMapping {

    String description() default "";
}
