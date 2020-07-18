package com.fanxl.admin.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fanxl.admin.excel.bean.StallExcelBean;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:46
 */
@Slf4j
public class ExcelStallListener extends AnalysisEventListener {

    private List<StallExcelBean> dataList = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        StallExcelBean foodExcelBean = (StallExcelBean) object;
        dataList.add(foodExcelBean);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
       log.info("解析完毕");
    }

    public List<StallExcelBean> getDataList() {
        return dataList;
    }
}