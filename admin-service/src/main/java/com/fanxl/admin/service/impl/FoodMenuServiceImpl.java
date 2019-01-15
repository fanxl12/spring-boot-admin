package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.FoodMenuDao;
import com.fanxl.admin.entity.FoodMenu;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.FoodMenuService;
import com.fanxl.admin.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:46
 */
@Service
public class FoodMenuServiceImpl implements FoodMenuService {

    private static final String FOLDER_NAME = "images/menu/";

    @Autowired
    private FoodMenuDao foodMenuDao;

    @Autowired
    private AdminProperties adminProperties;

    @Override
    public List<FoodMenu> getAll() {
        return foodMenuDao.selectAll();
    }
    
    @Override
    public boolean create(FoodMenu foodMenu, MultipartFile file) {
        if (file.isEmpty()) {
            throw new AdminException(ResultEnum.FILE_NOT_EMPTY);
        }
        try {
            BufferedImage sourceImg = ImageIO.read(file.getInputStream());
            foodMenu.setWidth(sourceImg.getWidth());
            foodMenu.setHeight(sourceImg.getHeight());
            String fileName = FileUtil.saveFile(file, adminProperties.getFileUpload() + FOLDER_NAME);
            foodMenu.setUrl(FOLDER_NAME + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foodMenuDao.insert(foodMenu)>0;
    }

    @Override
    public boolean delete(Long id) {
        FoodMenu foodMenu = foodMenuDao.selectByPrimaryKey(id);
        if (foodMenu == null) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        if (foodMenuDao.deleteByPrimaryKey(id)>0) {
            return FileUtil.deleteFile(adminProperties.getFileUpload() + foodMenu.getUrl());
        }
        throw new AdminException(ResultEnum.FAIL);
    }

    @Override
    public boolean update(FoodMenu foodMenu) {
        return foodMenuDao.updateByPrimaryKeySelective(foodMenu)>0;
    }

    @Override
    public FoodMenu getById(Long id) {
        return foodMenuDao.selectByPrimaryKey(id);
    }
}
