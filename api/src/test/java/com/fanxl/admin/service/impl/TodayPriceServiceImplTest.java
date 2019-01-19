package com.fanxl.admin.service.impl;

import com.fanxl.admin.service.TodayPriceService;
import com.fanxl.admin.vo.TodayPriceVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/18 0018 21:28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TodayPriceServiceImplTest {

    @Autowired
    private TodayPriceService todayPriceService;

    @Test
    public void getList4Api() {
        Pageable pageable = new PageRequest(1, 10);
        PageInfo<TodayPriceVO> pageInfo = todayPriceService.getList4Api(pageable);
        Assert.assertNotNull(pageInfo);
    }
}