package com.qiangqiang;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class DubboSpringSecurityApplicationTests {

    @Test
    void contextLoads() {
        for (int i = 0; i < 2; i++) {
            String encode = new BCryptPasswordEncoder().encode("123456");
            System.out.println(encode);
        }


    }

}
