package com.fanxl.admin.service;

import com.fanxl.admin.dto.StarBoothDTO;
import com.fanxl.admin.entity.StarBooth;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface StarBoothService {

    /**
     * 保存
     * @param starBooth
     * @param file
     * @return
     */
    boolean create(StarBooth starBooth, MultipartFile file);

    /**
     * 查找详情
     * @param id
     * @return
     */
    StarBooth getById(Long id);

    /**
     * 获取所有
     * @return
     */
    List<StarBooth> getAll();

    /**
     * 更新
     * @param starBooth
     * @return
     */
    boolean update(StarBooth starBooth);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 查询明细摊位 分页
     * @param pageable
     * @return
     */
    PageInfo<StarBoothDTO> getWebList(Pageable pageable);

}
