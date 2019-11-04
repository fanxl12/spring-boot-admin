package com.fanxl.admin.service;

import com.fanxl.admin.entity.PesticideCheck;
import com.fanxl.admin.vo.PesticideCheckVO;
import com.fanxl.admin.vo.StockInVO;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface PesticideCheckService {

    /**
     * 导入数据
     * @param file
     * @return
     */
    boolean importData(MultipartFile file);

    /**
     * 查询列表
     * @param pageable
     * @return
     */
    PageInfo<PesticideCheck> getList(Pageable pageable);

    /**
     * 获取农残检测
     * @return
     */
    List<PesticideCheckVO> all();


}
