package com.qiangqiang;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.qiangqiang.mapper")
@EnableGlobalMethodSecurity(securedEnabled = true)  //开启security注解的使用
public class DubboSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSpringSecurityApplication.class, args);
    }

}
