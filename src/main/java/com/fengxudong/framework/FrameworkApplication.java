package com.fengxudong.framework;

import com.fengxudong.framework.init.scanner.RoleMappingScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.fengxudong.framework")
@EnableTransactionManagement
@RoleMappingScan(baseScanPackage = "classpath:com/fengxudong/framework")
public class FrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameworkApplication.class, args);
    }

}
