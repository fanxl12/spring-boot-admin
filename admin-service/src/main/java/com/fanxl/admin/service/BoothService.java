package com.fanxl.admin.service;

import com.fanxl.admin.dto.BoothDTO;
import com.fanxl.admin.entity.Booth;
import com.fanxl.admin.vo.BoothImageVO;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface BoothService {

    /**
     * 保存
     * @param booth
     * @param file
     * @param businessLicense
     * @param license
     * @param heathLicense
     * @return
     * @throws Exception
     */
    boolean create(Booth booth, MultipartFile file, MultipartFile businessLicense,
                   MultipartFile license, MultipartFile heathLicense) throws Exception;

    /**
     * 查找详情
     * @param id
     * @return
     */
    Booth getById(Long id);

    /**
     * 获取所有
     * @return
     */
    List<Booth> getAll();

    /**
     * 更新
     * @param booth
     * @param file
     * @return
     * @throws Exception
     */
    boolean update(Booth booth, MultipartFile file) throws Exception;

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
    PageInfo<BoothDTO> getWebList(Pageable pageable);

    /**
     * 获取摊位图片详情
     * @param id
     * @return
     */
    BoothImageVO getImageDetail(Long id);

}
