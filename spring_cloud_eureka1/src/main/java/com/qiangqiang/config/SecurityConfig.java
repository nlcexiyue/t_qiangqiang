package com.qiangqiang.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/24
 * \* Time: 13:55
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //用户登录配置
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        super.configure(http);
        //忽略这个请求
//        http.csrf().ignoringAntMatchers("/eureka/**");
        http.csrf().disable();
//        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();

    }


}