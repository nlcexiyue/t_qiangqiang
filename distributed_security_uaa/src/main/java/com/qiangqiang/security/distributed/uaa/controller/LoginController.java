package com.qiangqiang.security.distributed.uaa.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/24
 * \* Time: 9:03
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
public class LoginController {
    @GetMapping("/hello")
    @ResponseBody
    //修饰这个hello方法应该被具有admin或者normal的权限的用户访问，注意要加上ROLE_前缀
    @Secured({"ROLE_admin"})
    public String hello(){
        return "哈哈";
    }


    @GetMapping("/login")
    public String log(){
        return "/page/login.html";
    }
    @GetMapping("/main")
    @Secured({"ROLE_admin"})
    public String main(){
        return "/page/main.html";
    }
}