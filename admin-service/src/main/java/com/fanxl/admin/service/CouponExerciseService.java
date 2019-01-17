package com.fanxl.admin.service;

import com.fanxl.admin.entity.CouponExercise;
import com.fanxl.admin.vo.CouponExerciseVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface CouponExerciseService {

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
    List<CouponExercise> getAll();

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 查询
     * @param id
     * @return
     */
    CouponExercise getById(Long id);

    /**
     * 下载文件
     * @param id
     * @param response
     * @return
     */
    boolean download(Long id, HttpServletResponse response);

    /**
     * 获取优惠活动的图片
     * @return
     */
    CouponExerciseVO getCouponUrl();

}
