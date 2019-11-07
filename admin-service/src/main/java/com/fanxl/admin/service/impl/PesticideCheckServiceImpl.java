package com.fanxl.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.fanxl.admin.dao.PesticideCheckDao;
import com.fanxl.admin.entity.PesticideCheck;
import com.fanxl.admin.entity.StockIn;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.excel.bean.PesticideCheckExcelBean;
import com.fanxl.admin.excel.listener.ExcelPesticideCheckListener;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.PesticideCheckService;
import com.fanxl.admin.utils.SetValueUtils;
import com.fanxl.admin.vo.PesticideCheckVO;
import com.fanxl.admin.vo.StockInVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class PesticideCheckServiceImpl implements PesticideCheckService {

    @Autowired
    private PesticideCheckDao pesticideCheckDao;

    @Override
    public boolean importData(MultipartFile multipartFile) {
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();

            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new ExcelPesticideCheckListener();
            EasyExcel.read(inputStream, PesticideCheckExcelBean.class, listener).sheet().doRead();

            List<PesticideCheckExcelBean> pesticideCheckList = ((ExcelPesticideCheckListener) listener).getDataList();
            if (pesticideCheckList.size()==0) {
                throw new AdminException(ResultEnum.FILE_NOT_FOUND.getCode(), "未解析出数据");
            }

            List<PesticideCheck> pesticideChecks = pesticideCheckList.stream().map(item -> {
                PesticideCheck pesticideCheck = new PesticideCheck();
                BeanUtils.copyProperties(item, pesticideCheck);
                if (StringUtils.isNotBlank(item.getCheckValueStr())) {
                    pesticideCheck.setCheckValue(Double.parseDouble(item.getCheckValueStr().substring(0, item.getCheckValueStr().length()-1)));
                }
                SetValueUtils.setValue(pesticideCheck);
                return pesticideCheck;
            }).collect(Collectors.toList());
            pesticideCheckDao.saveList(pesticideChecks);
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
    public PageInfo<PesticideCheck> getList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<PesticideCheck> list = pesticideCheckDao.selectAll();
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }

    @Override
    public List<PesticideCheckVO> all() {
        List<PesticideCheck> list = pesticideCheckDao.selectAll();
        return list.stream().map(item -> {
            PesticideCheckVO pesticideCheckVO = new PesticideCheckVO();
            BeanUtils.copyProperties(item, pesticideCheckVO);
            return pesticideCheckVO;
        }).collect(Collectors.toList());
    }
}
