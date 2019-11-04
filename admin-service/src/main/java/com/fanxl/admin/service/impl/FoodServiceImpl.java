package com.fanxl.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.fanxl.admin.dao.FoodDao;
import com.fanxl.admin.entity.Food;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.excel.bean.FoodExcelBean;
import com.fanxl.admin.excel.bean.PesticideCheckExcelBean;
import com.fanxl.admin.excel.listener.FoodExcelListener;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.FoodService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:56
 */
@Slf4j
@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDao foodDao;

    @Override
    public boolean importData(MultipartFile multipartFile) {
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();

            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new FoodExcelListener();
            EasyExcel.read(inputStream, PesticideCheckExcelBean.class, listener).sheet().doRead();

            List<FoodExcelBean> foodList = ((FoodExcelListener) listener).getDatas();
            log.info("数据:{}", foodList.size());
            if (foodList.size()==0) {
                throw new AdminException(ResultEnum.FILE_NOT_FOUND.getCode(), "未解析出数据");
            }
            List<Food> foods = foodList.stream().map(item -> {
                Food food = new Food();
                BeanUtils.copyProperties(item, food);
                return food;
            }).collect(Collectors.toList());
            return foodDao.saveList(foods)>0;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public PageInfo<Food> getList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<Food> list = foodDao.selectAll();
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }
}
