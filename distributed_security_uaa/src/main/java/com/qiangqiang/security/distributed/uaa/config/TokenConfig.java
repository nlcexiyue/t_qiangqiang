package com.qiangqiang.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class TokenConfig {

    //持久化令牌的方式,这里选择的是内存持久化方式
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

}
