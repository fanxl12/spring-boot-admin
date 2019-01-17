package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.CouponExerciseDao;
import com.fanxl.admin.entity.CouponExercise;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.CouponExerciseService;
import com.fanxl.admin.utils.FileUtil;
import com.fanxl.admin.vo.CouponExerciseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/11 0011 17:58
 */
@Slf4j
@Service
public class CouponExerciseServiceImpl implements CouponExerciseService {

    private static final String FOLDER_NAME = "images/coupon/";

    @Autowired
    private CouponExerciseDao couponExerciseDao;

    @Autowired
    private AdminProperties adminProperties;

    @Override
    public boolean save(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AdminException(ResultEnum.FILE_NOT_EMPTY);
        }
        try {
            CouponExercise couponExercise = new CouponExercise();
            BufferedImage sourceImg = ImageIO.read(file.getInputStream());
            couponExercise.setWidth(sourceImg.getWidth());
            couponExercise.setHeight(sourceImg.getHeight());

            String fileName = FileUtil.saveFile(file, adminProperties.getFileUpload() + FOLDER_NAME);
            couponExercise.setUrl(FOLDER_NAME + fileName);
            return couponExerciseDao.insertSelective(couponExercise)>0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CouponExercise> getAll() {
        return couponExerciseDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        CouponExercise couponExercise = couponExerciseDao.selectByPrimaryKey(id);
        if (couponExercise == null) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        if (couponExerciseDao.deleteByPrimaryKey(id)>0) {
            return FileUtil.deleteFile(adminProperties.getFileUpload() + couponExercise.getUrl());
        }
        throw new AdminException(ResultEnum.FAIL);
    }

    @Override
    public CouponExercise getById(Long id) {
        return couponExerciseDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean download(Long id, HttpServletResponse response) {
        CouponExercise couponExercise = getById(id);
        File file = new File(adminProperties.getFileUpload() + couponExercise.getUrl());
        if (!file.exists()) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());

        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public CouponExerciseVO getCouponUrl() {
        CouponExercise exercise = couponExerciseDao.getCoupon();
        if (exercise == null) {
            return null;
        }
        CouponExerciseVO couponExerciseVO = new CouponExerciseVO();
        BeanUtils.copyProperties(exercise, couponExerciseVO);
        return couponExerciseVO;
    }
}
