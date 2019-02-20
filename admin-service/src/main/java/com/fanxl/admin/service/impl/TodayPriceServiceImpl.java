package com.fanxl.admin.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.fanxl.admin.dao.TodayPriceDao;
import com.fanxl.admin.entity.TodayPrice;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.excel.bean.TodayPriceExcelBean;
import com.fanxl.admin.excel.listener.ExcelTodayPriceListener;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.TodayPriceService;
import com.fanxl.admin.vo.TodayPriceVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:56
 */
@Slf4j
@Service
public class TodayPriceServiceImpl implements TodayPriceService {

    @Autowired
    private TodayPriceDao todayPriceDao;

    @Override
    public boolean importData(MultipartFile multipartFile) {
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();

            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new ExcelTodayPriceListener();

            ExcelReader excelReader = new ExcelReader(inputStream, null, listener);

            excelReader.read(new Sheet(1, 1, TodayPriceExcelBean.class));

            List<TodayPriceExcelBean> todayPriceList = ((ExcelTodayPriceListener) listener).getDataList();
            if (todayPriceList.size()==0) {
                throw new AdminException(ResultEnum.FILE_NOT_FOUND.getCode(), "未解析出数据");
            }

            List<TodayPrice> todayPrices = todayPriceList.stream().map(item -> {
                TodayPrice todayPrice = new TodayPrice();
                BeanUtils.copyProperties(item, todayPrice);
                if (todayPrice.getPriceDate()==null) {
                    todayPrice.setPriceDate(new Date());
                } else {
                    log.info("价格时间:" + DateFormatUtils.format(todayPrice.getPriceDate(), "yyyy-MM-dd HH:mm:ss"));
                }
                return todayPrice;
            }).collect(Collectors.toList());
            return todayPriceDao.saveList(todayPrices)>0;
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
    public PageInfo<TodayPrice> getList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<TodayPrice> list = todayPriceDao.selectAll();
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }

    @Override
    public PageInfo<TodayPriceVO> getList4Api(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        String time = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        Map<String, Object> param = new HashMap<>();
        param.put("start", time + " 00:00:00");
        param.put("end", time + " 23:59:59");

        List<TodayPriceVO> list = todayPriceDao.list(param);
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
