package com.fanxl.admin.dao;

import com.fanxl.admin.dto.BoothDTO;
import com.fanxl.admin.entity.Booth;
import com.fanxl.admin.vo.BoothImageVO;
import com.fanxl.admin.vo.BoothVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface BoothDao extends Mapper<Booth> {


    /**
     * 获取web页面上显示的列表
     * @return
     */
    List<BoothDTO> getWebList();

    /**
     * 获取5星级摊位
     * @return
     */
    List<BoothVO> getList();

    /**
     * 获取图片的详情
     * @param id
     * @return
     */
    BoothImageVO imageDetail(Long id);


}
