package com.fanxl.admin.service;

import com.fanxl.admin.entity.TodayPrice;
import com.fanxl.admin.vo.TodayPriceVO;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface TodayPriceService {

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
    PageInfo<TodayPrice> getList(Pageable pageable);

    /**
     * 查询所有分类 分页
     * @return
     */
    List<TodayPriceVO> getList4Api();

}
