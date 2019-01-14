package com.fanxl.admin.service.impl;

import com.fanxl.admin.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 * @author: fanxl
 * @date: 2018/4/28 18:08
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodServiceImplTest {

    @Autowired
    private FoodService foodService;

    @Test
    public void importData() {
//        File file = new File("E://123.xlsx");
//        foodService.importData(file);
    }
}