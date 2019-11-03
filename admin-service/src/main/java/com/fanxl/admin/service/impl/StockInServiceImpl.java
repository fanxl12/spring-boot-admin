package com.fanxl.admin.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.fanxl.admin.dao.StockInDao;
import com.fanxl.admin.entity.StockIn;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.excel.bean.StockInExcelBean;
import com.fanxl.admin.excel.listener.ExcelStockInListener;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.StockInService;
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
public class StockInServiceImpl implements StockInService {

    @Autowired
    private StockInDao stockInDao;

    @Override
    public boolean importData(MultipartFile multipartFile) {
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();

            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new ExcelStockInListener();

            ExcelReader excelReader = new ExcelReader(inputStream, null, listener);

            excelReader.read(new Sheet(1, 1, StockInExcelBean.class));

            List<StockInExcelBean> stockInList = ((ExcelStockInListener) listener).getDataList();
            if (stockInList.size()==0) {
                throw new AdminException(ResultEnum.FILE_NOT_FOUND.getCode(), "未解析出数据");
            }

            List<StockIn> stockIns = stockInList.stream().map(item -> {
                StockIn stockIn = new StockIn();
                BeanUtils.copyProperties(item, stockIn);
                return stockIn;
            }).collect(Collectors.toList());
            return stockInDao.saveList(stockIns)>0;
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
    public PageInfo<StockIn> getList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<StockIn> list = stockInDao.selectAll();
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }
}
