package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.AdvertImageDao;
import com.fanxl.admin.entity.AdvertImage;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.AdvertImageService;
import com.fanxl.admin.utils.FileUtil;
import com.fanxl.admin.vo.AdvertImageVO;
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
import java.util.stream.Collectors;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/11 0011 17:58
 */
@Slf4j
@Service
public class AdvertImageServiceImpl implements AdvertImageService {

    private static final String FOLDER_NAME = "images/advert/";

    @Autowired
    private AdvertImageDao advertImageDao;

    @Autowired
    private AdminProperties adminProperties;

    @Override
    public boolean save(MultipartFile file) {
        if (file.isEmpty()) {
            throw new AdminException(ResultEnum.FILE_NOT_EMPTY);
        }
        try {
            AdvertImage advertImage = new AdvertImage();
            BufferedImage sourceImg = ImageIO.read(file.getInputStream());
            advertImage.setWidth(sourceImg.getWidth());
            advertImage.setHeight(sourceImg.getHeight());

            String fileName = FileUtil.saveFile(file, adminProperties.getFileUpload() + FOLDER_NAME);
            advertImage.setUrl(FOLDER_NAME + fileName);
            return advertImageDao.insertSelective(advertImage)>0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<AdvertImage> getAll() {
        return advertImageDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        AdvertImage advertImage = advertImageDao.selectByPrimaryKey(id);
        if (advertImage == null) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        if (advertImageDao.deleteByPrimaryKey(id)>0) {
            return FileUtil.deleteFile(adminProperties.getFileUpload() + advertImage.getUrl());
        }
        throw new AdminException(ResultEnum.FAIL);
    }

    @Override
    public List<AdvertImageVO> getAdvertList() {
        return advertImageDao.getAdvert().stream().map(item -> {
            AdvertImageVO advertImageVO = new AdvertImageVO();
            BeanUtils.copyProperties(item, advertImageVO);
            return advertImageVO;
        }).collect(Collectors.toList());
    }
}
