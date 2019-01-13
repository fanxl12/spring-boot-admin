package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.StarBoothDao;
import com.fanxl.admin.dto.StarBoothDTO;
import com.fanxl.admin.entity.StarBooth;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.StarBoothService;
import com.fanxl.admin.utils.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:46
 */
@Service
public class StarBoothServiceImpl implements StarBoothService {

    private static final String FOLDER_NAME = "images/booth/";

    @Autowired
    private StarBoothDao starBoothDao;

    @Autowired
    private AdminProperties adminProperties;

    @Override
    public List<StarBooth> getAll() {
        return starBoothDao.selectAll();
    }

    @Override
    public boolean create(StarBooth starBooth, MultipartFile file) {
        try {
            String fileName = FileUtil.saveFile(file, adminProperties.getFileUpload() + FOLDER_NAME);
            starBooth.setUrl(FOLDER_NAME + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return starBoothDao.insert(starBooth)>0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        StarBooth starBooth = starBoothDao.selectByPrimaryKey(id);
        if (starBooth == null) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        if (starBoothDao.deleteByPrimaryKey(id)>0) {
            return FileUtil.deleteFile(adminProperties.getFileUpload() + starBooth.getUrl());
        }
        throw new AdminException(ResultEnum.FAIL);
    }

    @Override
    public PageInfo<StarBoothDTO> getWebList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<StarBoothDTO> list = starBoothDao.getWebList();
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }

    @Override
    public boolean update(StarBooth starBooth) {
        return starBoothDao.updateByPrimaryKeySelective(starBooth)>0;
    }

    @Override
    public StarBooth getById(Long id) {
        return starBoothDao.selectByPrimaryKey(id);
    }
}
