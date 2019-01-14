package com.fanxl.admin.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface ImportService {

    /**
     * 导入数据
     * @param action
     * @param file
     * @return
     */
    boolean create(String action, MultipartFile file);
}
