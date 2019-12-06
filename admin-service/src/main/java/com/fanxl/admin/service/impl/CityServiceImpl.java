package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.CityDao;
import com.fanxl.admin.entity.City;
import com.fanxl.admin.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:46
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getAll() {
        return cityDao.selectAll();
    }
    
    @Override
    public boolean create(City city) {
        return cityDao.insert(city)>0;
    }

    @Override
    public boolean delete(Long id) {
        return cityDao.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean update(City city) {
        return cityDao.updateByPrimaryKeySelective(city)>0;
    }

    @Override
    public City getById(Long id) {
        return cityDao.selectByPrimaryKey(id);
    }
}
