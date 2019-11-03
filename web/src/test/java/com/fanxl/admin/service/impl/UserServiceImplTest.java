package com.fanxl.admin.service.impl;

import com.fanxl.admin.entity.User;
import com.fanxl.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @description
 * @author: fanxl
 * @date: 2018/4/28 18:08
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void create() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setPassword("123");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setSalt("123");
        user.setPhone("17621190028");
        user.setUsername("fanxl12");
        user.setEmail("1964645988@qq.com");
        user.setHead("http://www.baidu.com");
        user.setLastLoginTime(new Date());
        user.setAccountExpired(DateUtils.parseDate("2019-05-10 23:59:00", "yyyy-MM-dd HH:mm:ss"));
        boolean result = userService.create(user);
        Assert.assertEquals(true, result);
    }
}