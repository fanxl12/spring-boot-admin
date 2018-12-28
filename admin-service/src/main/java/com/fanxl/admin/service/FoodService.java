package com.fanxl.admin.service;

import java.io.File;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface FoodService {

    /**
     * 导入数据
     * @param file
     * @return
     */
    boolean importData(File file);

}
