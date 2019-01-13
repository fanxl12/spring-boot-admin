package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.WheelPictureDao;
import com.fanxl.admin.entity.WheelPicture;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.WheelPictureService;
import com.fanxl.admin.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/11 0011 17:58
 */
@Slf4j
@Service
public class WheelPictureServiceImpl implements WheelPictureService {

    private static final String FOLDER_NAME = "images/wheel/";

    @Autowired
    private WheelPictureDao wheelPictureDao;

    @Autowired
    private AdminProperties adminProperties;

    @Override
    public boolean saveList(MultipartFile[] fileList) {
        if (fileList == null || fileList.length == 0) {
            throw new AdminException(ResultEnum.FILE_NOT_EMPTY);
        }
        List<WheelPicture> dataList = new ArrayList<>();
        for (MultipartFile multipartFile : fileList) {
            try {
                WheelPicture wheelPicture = new WheelPicture();
                BufferedImage sourceImg = ImageIO.read(multipartFile.getInputStream());
                wheelPicture.setWidth(sourceImg.getWidth());
                wheelPicture.setHeight(sourceImg.getHeight());

                String fileName = FileUtil.saveFile(multipartFile, adminProperties.getFileUpload() + FOLDER_NAME);
                wheelPicture.setUrl(FOLDER_NAME + fileName);
                dataList.add(wheelPicture);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (dataList.size()==0) {
            throw new AdminException(ResultEnum.FILE_CREATE_FAIL);
        }
        return wheelPictureDao.saveList(dataList)>0;
    }

    @Override
    public List<WheelPicture> getAll() {
        return wheelPictureDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        WheelPicture wheelPicture = wheelPictureDao.selectByPrimaryKey(id);
        if (wheelPicture == null) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        if (wheelPictureDao.deleteByPrimaryKey(id)>0) {
            return FileUtil.deleteFile(adminProperties.getFileUpload() + wheelPicture.getUrl());
        }
        throw new AdminException(ResultEnum.FAIL);
    }
}
