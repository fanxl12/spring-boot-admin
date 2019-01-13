package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.BoothDao;
import com.fanxl.admin.dto.BoothDTO;
import com.fanxl.admin.entity.Booth;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.BoothService;
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
public class BoothServiceImpl implements BoothService {

    private static final String FOLDER_NAME = "images/booth/";

    @Autowired
    private BoothDao boothDao;

    @Autowired
    private AdminProperties adminProperties;

    @Override
    public List<Booth> getAll() {
        return boothDao.selectAll();
    }

    @Override
    public boolean create(Booth booth, MultipartFile file, MultipartFile businessLicense, MultipartFile license, MultipartFile heathLicense) {
        try {
            String fileName = FileUtil.saveFile(file, adminProperties.getFileUpload() + FOLDER_NAME);
            String businessLicenseName = FileUtil.saveFile(businessLicense, adminProperties.getFileUpload() + FOLDER_NAME);
            booth.setUrl(FOLDER_NAME + fileName);
            booth.setBusinessLicense(FOLDER_NAME + businessLicenseName);
            if (license != null) {
                String licenseName = FileUtil.saveFile(license, adminProperties.getFileUpload() + FOLDER_NAME);
                booth.setLicense(FOLDER_NAME + licenseName);
            }
            if (heathLicense != null) {
                String heathLicenseName = FileUtil.saveFile(heathLicense, adminProperties.getFileUpload() + FOLDER_NAME);
                booth.setHeathLicense(FOLDER_NAME + heathLicenseName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boothDao.insert(booth)>0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        Booth booth = boothDao.selectByPrimaryKey(id);
        if (booth == null) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        if (boothDao.deleteByPrimaryKey(id)>0) {
            FileUtil.deleteFile(adminProperties.getFileUpload() + booth.getUrl());
            FileUtil.deleteFile(adminProperties.getFileUpload() + booth.getBusinessLicense());
            FileUtil.deleteFile(adminProperties.getFileUpload() + booth.getLicense());
            FileUtil.deleteFile(adminProperties.getFileUpload() + booth.getHeathLicense());
            return true;
        }
        throw new AdminException(ResultEnum.FAIL);
    }

    @Override
    public PageInfo<BoothDTO> getWebList(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<BoothDTO> list = boothDao.getWebList();
        PageInfo pageInfo = new PageInfo<>(list, 6);
        return pageInfo;
    }

    @Override
    public boolean update(Booth booth) {
        return boothDao.updateByPrimaryKeySelective(booth)>0;
    }

    @Override
    public Booth getById(Long id) {
        return boothDao.selectByPrimaryKey(id);
    }
}
