package com.qiangqiang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/24
 * \* Time: 14:17
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Configuration
@EnableWebSecurity      //启用web安全的注解，此注解中引入了springSecurityFilterChain过滤器，这是springsecurity的核心过滤器，这是请求的认证入口
public class SecurityConfigSQL extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


    //自定义配置登录用户,可查询出用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //注入的加密实现类,不写的话上面的那个BCryptPasswordEncoder类会报错找不到
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //自定义的验证页面
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //退出登录的请求地址和退出成功后跳转的页面
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/page/main.html").permitAll();
        //自定义403权限不足的页面
        http.exceptionHandling().accessDeniedPage("/page/403.html");
        //表单登录
        http.formLogin()
                .loginPage("/page/login.html")    //登录页面设置
                .loginProcessingUrl("/login")   //登录访问的路径
                .defaultSuccessUrl("/page/main.html").permitAll()    //登录成功后,跳转路径

                .and().authorizeRequests()      //开启登录选择认证
                .antMatchers("/log").permitAll()  //设置哪些路径不需要认证,这里也能放行静态资源
//                .antMatchers("/hello").hasAuthority("ROLE_admin")    //这里是对hello这个方法做权限设置
                .anyRequest().authenticated()         //表示剩余其他接口,登录之后就能访问

                //开启记住我登录认证
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60);   //设置有效时长
//                .userDetailsService(userDetailsService);

//                .and().csrf().disable();                 //关闭csrf防护


    }

    //这里是放行静态资源的
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/public/**");
    }
}