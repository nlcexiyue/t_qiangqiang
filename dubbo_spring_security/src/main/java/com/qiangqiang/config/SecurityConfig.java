package com.qiangqiang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/24
 * \* Time: 13:55
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //用户登录配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {




        super.configure(http);


    }

    //重写这个方法配置用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String password = bCryptPasswordEncoder.encode("123456");
        auth.inMemoryAuthentication().
                withUser("user").password(password).roles("role1")
                //加上一个用户
                .and()
                .withUser("admin").password(password).roles("role2");
    }

    //注入的加密实现类,不写的话上面的那个BCryptPasswordEncoder类会报错找不到
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}