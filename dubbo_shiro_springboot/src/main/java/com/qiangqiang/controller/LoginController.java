package com.qiangqiang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/24
 * \* Time: 10:27
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class LoginController {

    @RequestMapping(value = "login")
    public void login(String username ,String password){

        Subject subject = SecurityUtils.getSubject();
        //如果还未认证
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            try {
                subject.login(token);
            } catch (UnknownAccountException uae) {
                System.out.println("没有该用户： " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println(token.getPrincipal() + " 的密码不正确!");
            }
        }
        if (subject.isAuthenticated()) {
            System.out.println("用户 " + subject.getPrincipal() + " 登录成功");
        }
        //是否有role1这个角色
        if (subject.hasRole("role1")) {
            System.out.println("有角色role1");
        } else {
            System.out.println("没有角色role1");
        }
        //是否有方法的执行权限
        if (subject.isPermitted("user:print")) {
            System.out.println("可以执行方法");
        } else {
            System.out.println("不能执行方法");
        }
    }
}