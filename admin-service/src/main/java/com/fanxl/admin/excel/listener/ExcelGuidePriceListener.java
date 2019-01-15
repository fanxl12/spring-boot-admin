package com.fanxl.admin.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fanxl.admin.excel.bean.GuidePriceExcelBean;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:46
 */
@Slf4j
public class ExcelGuidePriceListener extends AnalysisEventListener {

    private List<GuidePriceExcelBean> dataList = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        GuidePriceExcelBean foodExcelBean = (GuidePriceExcelBean) object;
        dataList.add(foodExcelBean);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
       log.info("解析完毕");
    }

    public List<GuidePriceExcelBean> getDataList() {
        return dataList;
    }
}