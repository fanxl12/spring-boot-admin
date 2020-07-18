package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.PlaneDao;
import com.fanxl.admin.entity.Plane;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.PlaneService;
import com.fanxl.admin.utils.FileUtil;
import com.fanxl.admin.vo.PlaneVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/11 0011 17:58
 */
@Slf4j
@Service
public class PlaneServiceImpl implements PlaneService {

    private static final String FOLDER_NAME = "images/plane/";

    @Autowired
    private PlaneDao planeDao;

    @Autowired
    private AdminProperties adminProperties;

    @Override
    public boolean save(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AdminException(ResultEnum.FILE_NOT_EMPTY);
        }
        try {
            Plane plane = new Plane();
            BufferedImage sourceImg = ImageIO.read(file.getInputStream());
            plane.setWidth(sourceImg.getWidth());
            plane.setHeight(sourceImg.getHeight());

            String fileName = FileUtil.saveFile(file, adminProperties.getFileUpload() + FOLDER_NAME);
            plane.setUrl(FOLDER_NAME + fileName);
            return planeDao.insertSelective(plane)>0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Plane> getAll() {
        return planeDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        Plane plane = planeDao.selectByPrimaryKey(id);
        if (plane == null) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        if (planeDao.deleteByPrimaryKey(id)>0) {
            return FileUtil.deleteFile(adminProperties.getFileUpload() + plane.getUrl());
        }
        throw new AdminException(ResultEnum.FAIL);
    }

    @Override
    public PlaneVO getOne() {
        Plane plane = planeDao.getOne();
        if (plane != null) {
            PlaneVO planeVO = new PlaneVO();
            BeanUtils.copyProperties(plane, planeVO);
            return planeVO;
        }
        return null;
    }
}
