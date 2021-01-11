package com.qiangqiang.handler;

import com.qiangqiang.common.Result;
import com.qiangqiang.entity.User;
import com.qiangqiang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userMapper.selectByLoginName(name);
//        request.getSession().setAttribute("userId",user.getId());
        Result result = new Result(200, "登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(result.toString());
    }
}
