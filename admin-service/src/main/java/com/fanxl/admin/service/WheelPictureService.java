package com.fanxl.admin.service;

import com.fanxl.admin.entity.WheelPicture;
import com.fanxl.admin.vo.WheelPictureVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 获取所有图片
     * @return
     */
    List<WheelPicture> getAll();

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 获取轮播图
     * @return
     */
    List<WheelPictureVO> getWheel();



}
