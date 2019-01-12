package com.fanxl.admin.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface WheelPictureService {

    /**
     * 保存多张图片
     * @param fileList
     * @return
     */
    boolean saveList(MultipartFile[] fileList);

}
