package com.qiangqiang.crm.interceptors;


import com.qiangqiang.crm.exceptions.NoLoginException;
import com.qiangqiang.crm.service.UserService;
import com.qiangqiang.crm.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         *  获取cookie 解析用户id
         *     如果用户id 存在 并且数据库中存在对应记录 请求合法   反之 用户未登录 请求非法
         */
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        if(userId==0 || null== userService.selectByPrimaryKey(userId)){
            throw  new NoLoginException();
        }
        return super.preHandle(request, response, handler);
    }
}
