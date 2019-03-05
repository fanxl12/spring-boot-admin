package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.FoodMenuDao;
import com.fanxl.admin.dto.ImageDTO;
import com.fanxl.admin.entity.FoodMenu;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.FoodMenuService;
import com.fanxl.admin.utils.FileUtil;
import com.fanxl.admin.vo.FoodMenuDetailVO;
import com.fanxl.admin.vo.FoodMenuItemVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        saveFile(foodMenu, file);
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

    private void saveFile(FoodMenu foodMenu, MultipartFile file) {
        try {
            ImageDTO imageDTO = FileUtil.saveWidthAndHeightFile(file, adminProperties.getFileUpload() + FOLDER_NAME);
            foodMenu.setWidth(imageDTO.getWidth());
            foodMenu.setHeight(imageDTO.getHeight());
            foodMenu.setUrl(FOLDER_NAME + imageDTO.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(FoodMenu foodMenu, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            FileUtil.deleteFile(adminProperties.getFileUpload() + foodMenu.getUrl());
            saveFile(foodMenu, file);
        }
        return foodMenuDao.updateByPrimaryKeySelective(foodMenu)>0;
    }

    @Override
    public FoodMenu getById(Long id) {
        return foodMenuDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<FoodMenuItemVO> getList(Long categoryId, String keyword, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<FoodMenuItemVO> list;
        if (categoryId == 0) {
            list = foodMenuDao.getPopularList(keyword);
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("categoryId", categoryId);
            param.put("keyword", keyword);
            list = foodMenuDao.getList(param);
        }
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public FoodMenuDetailVO getDetail(Long id) {
        FoodMenu foodMenu = foodMenuDao.selectByPrimaryKey(id);
        FoodMenuDetailVO detail = new FoodMenuDetailVO();
        BeanUtils.copyProperties(foodMenu, detail);
        return detail;
    }
}
