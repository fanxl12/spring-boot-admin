package com.fanxl.admin.service;

import com.fanxl.admin.entity.GuidePrice;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface GuidePriceService {

    /**
     * 导入数据
     * @param file
     * @return
     */
    boolean importData(MultipartFile file);

    /**
     * 查询所有分类 分页
     * @param pageable
     * @return
     */
    PageInfo<GuidePrice> getList(Pageable pageable);

}
