package com.qiangqiang.service.impl;

import com.qiangqiang.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/24
 * \* Time: 14:29
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    //下面重新的方法里的username是页面表单中的传递过来的用户名

    @Override
    public UserDetails loadUserByUsername(String username) {

        //设置账号有个role1的角色
        List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin");

        com.qiangqiang.entity.User user = new com.qiangqiang.entity.User();
        user.setLoginname("zhangsan");
        user.setPwd("password");
//        com.qiangqiang.entity.User user = userMapper.selectByLoginName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        //记录一下，这个地方的查询的密码是明文，并没有加密，那个凭证每次都不一样
        return new User(user.getLoginname(),new BCryptPasswordEncoder().encode(user.getPwd()),role);
    }
}