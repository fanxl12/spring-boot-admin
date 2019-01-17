package com.fanxl.admin.service;

import com.fanxl.admin.entity.AdvertImage;
import com.fanxl.admin.vo.AdvertImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface AdvertImageService {

    /**
     * 保存多张图片
     * @param file
     * @return
     */
    boolean save(MultipartFile file);

    /**
     * 获取所有图片
     * @return
     */
    List<AdvertImage> getAll();

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 获取广告
     * @return
     */
    List<AdvertImageVO> getAdvertList();
}
