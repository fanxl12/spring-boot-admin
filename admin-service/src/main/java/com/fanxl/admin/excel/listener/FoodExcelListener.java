package com.fanxl.admin.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fanxl.admin.excel.bean.FoodExcelBean;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/* 解析监听器，
 * 每解析一行会回调invoke()方法。
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 *
 * 下面只是我写的一个样例而已，可以根据自己的逻辑修改该类。
 * @author jipengfei
 * @date 2017/03/14
 */
@Slf4j
public class FoodExcelListener extends AnalysisEventListener {

    private List<FoodExcelBean> datas = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        log.info("当前行:{}", context.getCurrentRowNum());

        FoodExcelBean foodExcelBean = (FoodExcelBean) object;

        datas.add(foodExcelBean);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
       log.info("解析完毕");
    }

    public List<FoodExcelBean> getDatas() {
        return datas;
    }
}