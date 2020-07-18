package com.fanxl.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fanxl.admin.dao.StallDao;
import com.fanxl.admin.entity.Stall;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.excel.bean.StallExcelBean;
import com.fanxl.admin.excel.listener.ExcelStallListener;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.StallService;
import com.fanxl.admin.vo.StallVO;
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
public class StallServiceImpl implements StallService {

    @Autowired
    private StallDao stallDao;

    @Override
    public boolean importData(MultipartFile multipartFile) {
        InputStream inputStream = null;
        try {

            inputStream = multipartFile.getInputStream();

            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new ExcelStallListener();

            EasyExcel.read(inputStream, StallExcelBean.class, listener).sheet().doRead();

            List<StallExcelBean> stallList = ((ExcelStallListener) listener).getDataList();
            if (stallList.size()==0) {
                throw new AdminException(ResultEnum.FILE_NOT_FOUND.getCode(), "未解析出数据");
            }
            stallDao.deleteAll();
            return stallDao.saveList(stallList.stream().map(item -> {
                Stall stall = new Stall();
                BeanUtils.copyProperties(item, stall);
                return stall;
            }).collect(Collectors.toList()))>0;
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
    public PageInfo<Stall> getList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<Stall> list = stallDao.selectAll();
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }

    @Override
    public PageInfo<StallVO> getList4Api(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<StallVO> list = stallDao.selectAll().stream().map(item -> {
            StallVO stall = new StallVO();
            BeanUtils.copyProperties(item, stall);
            return stall;
        }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }


}
