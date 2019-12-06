package com.fanxl.admin.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fanxl.admin.dao.GuidePriceDao;
import com.fanxl.admin.entity.GuidePrice;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.excel.bean.GuidePriceExcelBean;
import com.fanxl.admin.excel.listener.ExcelGuidePriceListener;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.FoodService;
import com.fanxl.admin.service.GuidePriceService;
import com.fanxl.admin.vo.GuidePriceVO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:56
 */
@Slf4j
@Service
public class GuidePriceServiceImpl implements GuidePriceService {

    @Autowired
    private GuidePriceDao guidePriceDao;

    @Autowired
    private FoodService foodService;

    @Override
    public boolean importData(MultipartFile multipartFile) {
        InputStream inputStream = null;
        try {

            inputStream = multipartFile.getInputStream();

            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new ExcelGuidePriceListener();

            EasyExcel.read(inputStream, GuidePriceExcelBean.class, listener).sheet().doRead();

            List<GuidePriceExcelBean> guidePriceList = ((ExcelGuidePriceListener) listener).getDataList();
            if (guidePriceList.size()==0) {
                throw new AdminException(ResultEnum.FILE_NOT_FOUND.getCode(), "未解析出数据");
            }

            List<GuidePrice> lastPriceList = guidePriceDao.getLastGuidePrice(guidePriceDao.getLastPriceDate());

            List<GuidePrice> guidePrices = new ArrayList<>();
            for (GuidePriceExcelBean item : guidePriceList) {
                //先检查是否存在该食品，没有就创建
                item.setFoodId(foodService.checkFood(item.getFoodName(), item.getCategoryId()));

                GuidePrice guidePrice = new GuidePrice();
                BeanUtils.copyProperties(item, guidePrice);

                if (lastPriceList!=null && lastPriceList.size()>0) {
                    for (GuidePrice last : lastPriceList) {
                        if (item.getFoodId().equals(last.getFoodId())) {
                            guidePrice.setMaxPriceTrend(item.getMaxPrice().compareTo(last.getMaxPrice()));
                            guidePrice.setLowPriceTrend(item.getLowPrice().compareTo(last.getLowPrice()));
                            break;
                        }
                    }
                }
                if (guidePrice.getPriceDate()==null) {
                    guidePrice.setPriceDate(new Date());
                }
                guidePrices.add(guidePrice);
            }
            guidePriceDao.deleteAll();
            return guidePriceDao.saveList(guidePrices)>0;
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
    public PageInfo<GuidePrice> getList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<GuidePrice> list = guidePriceDao.selectAll();
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }

    @Override
    public PageInfo<GuidePriceVO> getList4Api(Pageable pageable, Long categoryId) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<GuidePriceVO> list = guidePriceDao.list(categoryId);
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }

    @Override
    public List<GuidePriceVO> all() {
        return guidePriceDao.all();
    }
}
