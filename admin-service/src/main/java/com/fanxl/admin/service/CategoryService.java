package com.fanxl.admin.service;

import com.fanxl.admin.entity.Category;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface CategoryService {

    /**
     * 查询所有分类
     * @param pageable
     * @return
     */
    PageInfo<Category> findAll(Pageable pageable);

    /**
     * 创建分类
     * @param category
     * @return
     */
    boolean create(Category category);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 更新
     * @param category
     * @return
     */
    boolean update(Category category);

}
