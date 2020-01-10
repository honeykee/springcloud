package com.honeykee.auth.server;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-03 11:16
 * @since JDK 1.8
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class BCryptPasswordTest {
    @Test
    public void test() {
        String pwd = "secret";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encodedPassword = passwordEncoder.encode(pwd);
        log.info("【加密后的密码为：】" + encodedPassword);
    }
}
