package com.fanxl.admin.service;

import com.fanxl.admin.entity.Market;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface MarketService {

    /**
     * 查询所有分类
     * @return
     */
    List<Market> getAll();

    
    /**
     * 创建分类
     * @param market
     * @return
     */
    boolean create(Market market);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 更新
     * @param market
     * @return
     */
    boolean update(Market market);

    /**
     * 查询详情
     * @param id
     * @return
     */
    Market getById(Long id);

}
