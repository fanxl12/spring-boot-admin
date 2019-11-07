package com.fanxl.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.fanxl.admin.dao.StockInDao;
import com.fanxl.admin.entity.StockIn;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.excel.bean.PesticideCheckExcelBean;
import com.fanxl.admin.excel.bean.StockInExcelBean;
import com.fanxl.admin.excel.listener.ExcelPesticideCheckListener;
import com.fanxl.admin.excel.listener.ExcelStockInListener;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.StockInService;
import com.fanxl.admin.utils.SetValueUtils;
import com.fanxl.admin.vo.StockInVO;
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

            EasyExcel.read(inputStream, StockInExcelBean.class, listener).sheet().doRead();

            List<StockInExcelBean> stockInList = ((ExcelStockInListener) listener).getDataList();
            if (stockInList.size()==0) {
                throw new AdminException(ResultEnum.FILE_NOT_FOUND.getCode(), "未解析出数据");
            }

            List<StockIn> stockIns = stockInList.stream().map(item -> {
                StockIn stockIn = new StockIn();
                BeanUtils.copyProperties(item, stockIn);
                SetValueUtils.setValue(stockIn);
                return stockIn;
            }).collect(Collectors.toList());
            stockInDao.saveList(stockIns);
            return true;
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

    @Override
    public List<StockInVO> all() {
        List<StockIn> list = stockInDao.selectAll();
        return list.stream().map(item -> {
            StockInVO stockInVO = new StockInVO();
            BeanUtils.copyProperties(item, stockInVO);
            return stockInVO;
        }).collect(Collectors.toList());
    }
}
