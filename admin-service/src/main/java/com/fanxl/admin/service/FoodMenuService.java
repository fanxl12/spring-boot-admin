package com.fanxl.admin.service;

import com.fanxl.admin.entity.FoodMenu;
import com.fanxl.admin.vo.FoodMenuDetailVO;
import com.fanxl.admin.vo.FoodMenuItemVO;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface FoodMenuService {

    /**
     * 查询所有分类
     * @return
     */
    List<FoodMenu> getAll();
    
    /**
     * 创建分类
     * @param foodMenu
     * @param file
     * @return
     */
    boolean create(FoodMenu foodMenu, MultipartFile file);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 更新
     * @param foodMenu
     * @param file
     * @return
     */
    boolean update(FoodMenu foodMenu, MultipartFile file);

    /**
     * 查询详情
     * @param id
     * @return
     */
    FoodMenu getById(Long id);

    /**
     * 获取列表
     * @param categoryId
     * @param keyword
     * @param pageable
     * @return
     */
    PageInfo<FoodMenuItemVO> getList(Long categoryId, String keyword, Pageable pageable);

    /**
     * 查询详情
     * @param id
     * @return
     */
    FoodMenuDetailVO getDetail(Long id);

}
