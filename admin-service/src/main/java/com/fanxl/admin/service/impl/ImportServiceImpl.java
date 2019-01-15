package com.fanxl.admin.service.impl;

import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.FoodService;
import com.fanxl.admin.service.GuidePriceService;
import com.fanxl.admin.service.ImportService;
import com.fanxl.admin.service.TodayPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/14 0014 21:45
 */
@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    private FoodService foodService;

    @Autowired
    private GuidePriceService guidePriceService;

    @Autowired
    private TodayPriceService todayPriceService;

    @Override
    public boolean create(String action, MultipartFile file) {
        if (file.isEmpty()) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        if (action.equalsIgnoreCase("food")) {
            return foodService.importData(file);
        } else if (action.equalsIgnoreCase("guidePrice")) {
            return guidePriceService.importData(file);
        } else if (action.equalsIgnoreCase("todayPrice")) {
            return todayPriceService.importData(file);
        }
        return false;
    }
}
