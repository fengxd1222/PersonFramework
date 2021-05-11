package com.fengxudong.framework.init.scanner;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//spring中的注解,加载对应的类
@Import(RoleMappingScannerRegister.class)//这个是我们的关键，实际上也是由这个类来扫描的
@Documented
public @interface RoleMappingScan {
    String[] baseScanPackage() default {};
}
