package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.MarketRegionDao;
import com.fanxl.admin.entity.Market;
import com.fanxl.admin.entity.MarketRegion;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.MarketRegionService;
import com.fanxl.admin.service.MarketService;
import com.fanxl.admin.vo.MarketRegionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:46
 */
@Service
public class MarketRegionServiceImpl implements MarketRegionService {

    @Autowired
    private MarketRegionDao marketRegionDao;

    @Autowired
    private MarketService marketService;

    @Override
    public List<MarketRegion> getByMarkId(Long marketId) {
        Example example = new Example(MarketRegion.class);
        example.createCriteria().andEqualTo("marketId", marketId);
        return marketRegionDao.selectByExample(example);
    }

    @Override
    public List<MarketRegion> getAll() {
        return marketRegionDao.selectAll();
    }
    
    @Override
    public boolean create(MarketRegion marketRegion) {
        List<Market> marketList = marketService.getAll();
        if (marketList == null || marketList.size()==0) {
            throw new AdminException(1006, "请先创建菜市场");
        }
        marketRegion.setMarketId(marketList.get(0).getId());
        return marketRegionDao.insert(marketRegion)>0;
    }

    @Override
    public boolean delete(Long id) {
        return marketRegionDao.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean update(MarketRegion marketRegion) {
        return marketRegionDao.updateByPrimaryKeySelective(marketRegion)>0;
    }

    @Override
    public MarketRegion getById(Long id) {
        return marketRegionDao.selectByPrimaryKey(id);
    }

    @Override
    public List<MarketRegionVO> getList() {
        return marketRegionDao.list();
    }
}
