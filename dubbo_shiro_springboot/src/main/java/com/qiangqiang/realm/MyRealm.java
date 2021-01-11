package com.qiangqiang.realm;

import com.qiangqiang.ini.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/23
 * \* Time: 10:33
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MyRealm extends AuthorizingRealm {

//    @Autowired
//    private HashedCredentialsMatcher hashedCredentialsMatcher;

    //构造方法中加密,要是不写构造方法,就用shiro.ini中的加密配置
    public MyRealm() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        //指定加密算法
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        //指定散列次数
//        hashedCredentialsMatcher.setHashIterations(2);
//        this.setCredentialsMatcher(hashedCredentialsMatcher);


    }

    //获取身份信息,可以在这里从数据库中获取用户的权限和角色信息
    //这个可以调用多次数据库
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //这是下面传上来的
        User user = (User) this.getAvailablePrincipal(principals);
        if (!"zhangsan".equals(user.getUsername())) {
            throw new UnknownAccountException();
        }
        if (!"ed6c6bc213d0901cae002d64e8fb953e".equals(user.getPassword())) {
            throw new IncorrectCredentialsException();
        }


        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //权限
        Set<String> s = new HashSet<>();
        s.add("user:print");
        s.add("user:query");
        s.add("user:delete");
        simpleAuthorizationInfo.setStringPermissions(s);
        //如果是超级管理员,拥有所有权限,这样写
        //simpleAuthorizationInfo.addStringPermission("*:*");
        //角色
        Set<String> r = new HashSet<>();
        r.add("role1");
        simpleAuthorizationInfo.setRoles(r);


        return simpleAuthorizationInfo;
    }

    //身份认证,只在登录的时候调用一次
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        String username = token.getPrincipal().toString();
        System.out.println("用户名为:" + username);
        //获取密码
//        String password = token.getCredentials().toString();
        String password = new String((char[]) token.getCredentials());
        System.out.println("密码为" + password);

        User user = new User(username, "ed6c6bc213d0901cae002d64e8fb953e");



//        if (!"zhangsan".equals(username)) {
//            throw new UnknownAccountException();
//        }
//        if (!"ed6c6bc213d0901cae002d64e8fb953e".equals(password)) {
//            throw new IncorrectCredentialsException();
//        }

        //身份验证通过，返回一个身份信息
        //SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
        //principal这个对象传什么,后面的授权就会接收什么,这里可以将用户的权限角色都查询出来封装到map中,再传走
        ByteSource byteSource = ByteSource.Util.bytes("MaoZhuXi");
        //这里的user.getPassword()是从数据库中查询出来的已加密的密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),byteSource, this.getName());
        //加salt盐,一般盐是存在于用户对象中的
//        ByteSource byteSource = ByteSource.Util.bytes("盐");
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, byteSource,this.getName());


        return simpleAuthenticationInfo;
    }
}