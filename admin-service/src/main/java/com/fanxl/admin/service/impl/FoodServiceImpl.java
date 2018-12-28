package com.fanxl.admin.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.excel.bean.FoodExcelBean;
import com.fanxl.admin.excel.bean.FoodExcelListener;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:56
 */
@Slf4j
@Service
public class FoodServiceImpl implements FoodService {

    @Override
    public boolean importData(File file) {

        if (!file.exists()) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);

            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new FoodExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, null, listener);

            excelReader.read(new Sheet(1, 2, FoodExcelBean.class));

            List<FoodExcelBean> foodList = ((FoodExcelListener) listener).getDatas();
            log.info("数据:{}", foodList.size());
        } catch (FileNotFoundException e) {
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
}
