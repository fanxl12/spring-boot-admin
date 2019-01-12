package com.fanxl.admin.service.impl;

import com.fanxl.admin.service.WheelPictureService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/11 0011 18:16
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WheelPictureServiceImplTest {

    @Autowired
    private WheelPictureService wheelPictureService;

    @Test
    public void saveList() {
        wheelPictureService.saveList(null);
    }
}