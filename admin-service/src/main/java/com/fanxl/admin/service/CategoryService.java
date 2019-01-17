package com.fanxl.admin.service;

import com.fanxl.admin.entity.Category;
import com.fanxl.admin.vo.CategoryVO;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface CategoryService {

    /**
     * 查询所有分类
     * @return
     */
    List<Category> getAll();

    /**
     * 查询所有分类 分页
     * @param pageable
     * @return
     */
    PageInfo<Category> getList(Pageable pageable);

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

    /**
     * 查询详情
     * @param id
     * @return
     */
    Category getById(Long id);

    /**
     * 获取分类
     * @return
     */
    List<CategoryVO> getCategoryList();



}
