package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.CategoryDao;
import com.fanxl.admin.entity.Category;
import com.fanxl.admin.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:46
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageInfo<Category> findAll(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), 4);
        List<Category> list = categoryDao.selectAll();
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }

    @Override
    public boolean create(Category category) {
        return categoryDao.insert(category)>0;
    }

    @Override
    public boolean delete(Long id) {
        return categoryDao.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean update(Category category) {
        return categoryDao.updateByPrimaryKeySelective(category)>0;
    }

    @Override
    public Category getById(Long id) {
        return categoryDao.selectByPrimaryKey(id);
    }
}
