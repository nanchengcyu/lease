package cn.nanchengyu.lease.web.app.service.impl;

import cn.nanchengyu.lease.web.app.service.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassName: SmsServiceImplTest
 * Package: cn.nanchengyu.lease.web.app.service.impl
 * Description:
 *
 * @Author 南城余
 * @Create 2024/6/5 20:47
 * @Version 1.0
 */
@SpringBootTest
class SmsServiceImplTest {

    @Autowired
    private SmsService service;

    @Test
    void sendCode() {
        service.sendCode("12345678901","123456");
    }
}