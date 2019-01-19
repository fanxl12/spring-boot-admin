package com.fanxl.admin.service;

import com.fanxl.admin.entity.MarketRegion;
import com.fanxl.admin.vo.MarketRegionVO;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface MarketRegionService {

    /**
     * 查询所有分类
     * @param marketId
     * @return
     */
    List<MarketRegion> getByMarkId(Long marketId);

    /**
     * 查询所有分类
     * @return
     */
    List<MarketRegion> getAll();

    
    /**
     * 创建分类
     * @param marketRegion
     * @return
     */
    boolean create(MarketRegion marketRegion);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 更新
     * @param marketRegion
     * @return
     */
    boolean update(MarketRegion marketRegion);

    /**
     * 查询详情
     * @param id
     * @return
     */
    MarketRegion getById(Long id);

    /**
     * 查询菜场分区
     * @return
     */
    List<MarketRegionVO> getList();

}
