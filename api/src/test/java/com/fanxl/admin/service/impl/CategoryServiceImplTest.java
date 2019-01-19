package com.fanxl.admin.service.impl;

import com.fanxl.admin.entity.Category;
import com.fanxl.admin.service.CategoryService;
import com.fanxl.admin.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/18 0018 19:25
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void getAll() {
        List<Category> categoryVOList = categoryService.getAll();
        Assert.assertNotNull(categoryVOList);
    }

    @Test
    public void getCategoryList() {
        List<CategoryVO> categoryVOList = categoryService.getCategoryList();
        Assert.assertNotNull(categoryVOList);
    }
}