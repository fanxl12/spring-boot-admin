package com.fanxl.admin.dao;

import com.fanxl.admin.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface CategoryDao {

    /**
     * 创建分类
     * @param category
     * @return
     */
    int create(Category category);

    /**
     * 查询所有分类
     * @return
     */
    List<Category> findAll();

    /**
     * 查找分类详情
     * @param id
     * @return
     */
    Category get(String id);

    /**
     * 更新分类
     * @param category
     * @return
     */
    int update(Category category);

    /**
     * 删除分类
     * @param id
     * @return
     */
    int delete(Long id);

}
