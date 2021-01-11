package com.qiangqiang.security.distributed.uaa.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@EnableAuthorizationServer  //标识为一个授权服务
@Configuration
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    //持久化令牌的方式
    @Autowired
    private TokenStore tokenStore;

    //客户端配置服务
    @Autowired
    private ClientDetailsService clientDetailsService;

    //授权码服务
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    //认证管理器
    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }

    //配置令牌服务
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        //设置客户端信息服务
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        //是否产生刷新令牌
        defaultTokenServices.setSupportRefreshToken(true);
        //令牌存储策略
        defaultTokenServices.setTokenStore(tokenStore);
        //令牌默认有效期2小时    //不到期不刷新
        defaultTokenServices.setAccessTokenValiditySeconds(7200);
        //刷新令牌默认有效期3天
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);
        return defaultTokenServices;
    }



    public AuthorizationServer() {
        super();
    }

    //用来配置令牌端点的安全约束策略
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")      //oauth/token_key公开
                .checkTokenAccess("permitAll()")    //检测令牌  /oauth/check_token 完全公开
                .allowFormAuthenticationForClients();   //允许表单模式来申请令牌




    }

    //配置客户端详情服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //使用内存
        clients.inMemory()
                .withClient("c1")   //client_id0
                .secret(new BCryptPasswordEncoder().encode("secret"))   //客户端秘钥
                .resourceIds("res1")    //可以访问的资源列表
                .authorizedGrantTypes("authorization_code","password","refresh_token","implicit","client_credentials")      //该client允许的授权的类型,有5种authorization_code,password,refresh_token,implicit,client_credentials
                .scopes("all")  //允许授权的范围,可以看做是客户端的权限
                .autoApprove(false)     //false表示跳转到授权的页面
                .redirectUris("https://www.baidu.com");     //加上验证回调地址
    }


    //AuthorizationServerEndpointsConfigurer这个对象的实例可以完成令牌服务以及令牌endpoint配置
    //令牌访问endpoints端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManagerBean)   //认证管理器,密码模式
                .authorizationCodeServices(authorizationCodeServices)   //授权码模式
                .tokenServices(authorizationServerTokenServices())  //令牌管理模式,不管是谁都需要
                .allowedTokenEndpointRequestMethods(HttpMethod.GET);   //端点允许post提交


    }
//fb704fb5-30a0-48b4-83ab-a4a5b521d263
    //0767186f-4eba-4beb-a036-1848efbab9fb
    //http://localhost:53020/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=https://www.baidu.com
    //http://localhost:53020/oauth/token?client_id=c1&client_secret=secret&grant_type=authorization_code&code=XW9YqL&redirect_uri=https://www.baidu.com
}
