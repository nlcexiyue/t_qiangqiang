package com.qiangqiang.ini;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/23
 * \* Time: 9:30
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

//[users]
//        #用户zhangsan的密码是123456，此用户具有role1和role2两个角色
//        zhangsan=123456,role1,role2
//        lisi=123456,role2
//
//        [roles]
//        #role1具有create和delete权限
//        role1=user:create,user:delete
//        role2=user:create
public class loginIni {
    public static void main(String[] args) {

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //加载shiro.ini文件
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        //设置realm
        defaultSecurityManager.setRealm(iniRealm);
        //相当于ThreadLocal绑定当前线程
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //创建一个subject
        Subject subject = SecurityUtils.getSubject();
        //创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123456");
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("账户没找到");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //角色认证
        boolean authenticated = subject.isAuthenticated();
        System.out.println("是否认证通过:" + authenticated);
        if (authenticated) {
            System.out.println("subject" + subject);
            System.out.println("用户" + subject.getPrincipal());
            System.out.println("类型" + subject.getPrincipal().getClass());
        }

        //退出
        subject.logout();
        boolean authenticated1 = subject.isAuthenticated();
        System.out.println("退出后" + authenticated1);

    }


}