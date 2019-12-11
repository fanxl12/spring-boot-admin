package com.fanxl.admin.service;

import com.fanxl.admin.entity.City;
import com.fanxl.admin.vo.CityVO;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface CityService {

    /**
     * 查询所有
     * @return
     */
    List<City> getAll();

    
    /**
     * 创建
     * @param city
     * @return
     */
    boolean create(City city);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 更新
     * @param city
     * @return
     */
    boolean update(City city);

    /**
     * 查询详情
     * @param id
     * @return
     */
    City getById(Long id);

    /**
     * 获取一个城市
     * @return
     */
    CityVO getOneCity();

}
