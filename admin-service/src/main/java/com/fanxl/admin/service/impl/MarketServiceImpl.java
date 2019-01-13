package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.MarketDao;
import com.fanxl.admin.entity.Market;
import com.fanxl.admin.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:46
 */
@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketDao marketDao;

    @Override
    public List<Market> getAll() {
        return marketDao.selectAll();
    }
    
    @Override
    public boolean create(Market market) {
        return marketDao.insert(market)>0;
    }

    @Override
    public boolean delete(Long id) {
        return marketDao.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean update(Market market) {
        return marketDao.updateByPrimaryKeySelective(market)>0;
    }

    @Override
    public Market getById(Long id) {
        return marketDao.selectByPrimaryKey(id);
    }
}
