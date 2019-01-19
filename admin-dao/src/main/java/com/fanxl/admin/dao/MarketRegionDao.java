package com.fanxl.admin.dao;

import com.fanxl.admin.entity.MarketRegion;
import com.fanxl.admin.vo.MarketRegionVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface MarketRegionDao extends Mapper<MarketRegion> {

    /**
     * 获取菜市场区域
     * @return
     */
    List<MarketRegionVO> list();

}
