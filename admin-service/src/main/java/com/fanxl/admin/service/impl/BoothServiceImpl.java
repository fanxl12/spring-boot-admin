package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.BoothDao;
import com.fanxl.admin.dto.BoothDTO;
import com.fanxl.admin.dto.ImageDTO;
import com.fanxl.admin.entity.Booth;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.BoothService;
import com.fanxl.admin.utils.FileUtil;
import com.fanxl.admin.vo.BoothImageVO;
import com.fanxl.admin.vo.BoothVO;
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
    public boolean create(Booth booth, MultipartFile file, MultipartFile businessLicense,
                          MultipartFile license, MultipartFile heathLicense) throws Exception {

        if (file == null || file.isEmpty()) {
            throw new AdminException(40012, "摊位图片不能为空");
        }
        String folderPath = adminProperties.getFileUpload() + FOLDER_NAME;
        String fileName = FileUtil.saveFile(file, folderPath);
        booth.setUrl(FOLDER_NAME + fileName);

        ImageDTO businessImage = null;
        if (businessLicense!=null && !businessLicense.isEmpty()) {
            businessImage = FileUtil.saveWidthAndHeightFile(businessLicense, folderPath);
        } else if (booth.getBusinessLicense()!=null) {
            businessImage = FileUtil.downLoadImage(booth.getBusinessLicense(), folderPath);
        }
        if (businessImage != null) {
            booth.setBusinessWidth(businessImage.getWidth());
            booth.setBusinessHeight(businessImage.getHeight());
            booth.setBusinessLicense(FOLDER_NAME + businessImage.getName());
        }
        try {
            ImageDTO licenseImage = null;
            if (license != null && !license.isEmpty()) {
                licenseImage = FileUtil.saveWidthAndHeightFile(license, folderPath);
            } else if (booth.getLicense()!=null){
                licenseImage = FileUtil.downLoadImage(booth.getLicense(), folderPath);
            }
            if (licenseImage != null) {
                booth.setLicenseWidth(licenseImage.getWidth());
                booth.setLicenseHeight(licenseImage.getHeight());
                booth.setLicense(FOLDER_NAME + licenseImage.getName());
            }

            ImageDTO heathLicenseImage = null;
            if (heathLicense != null && !heathLicense.isEmpty()) {
                heathLicenseImage = FileUtil.saveWidthAndHeightFile(heathLicense, folderPath);
            } else if (booth.getHeathLicense()!=null){
                heathLicenseImage = FileUtil.downLoadImage(booth.getHeathLicense(), folderPath);
            }
            if (heathLicenseImage != null) {
                booth.setHeathWidth(heathLicenseImage.getWidth());
                booth.setHeathHeight(heathLicenseImage.getHeight());
                booth.setHeathLicense(FOLDER_NAME + heathLicenseImage.getName());
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
    public PageInfo<BoothVO> getList(Pageable pageable) {
        PageHelper.startPage(2, 10);
        List<BoothVO> list = boothDao.getList();
        PageInfo pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public BoothImageVO getImageDetail(Long id) {
        return boothDao.imageDetail(id);
    }

    @Override
    public boolean update(Booth booth, MultipartFile file) throws Exception {
        String oldFileUrl = null;
        if (file!=null && !file.isEmpty()) {
            String folderPath = adminProperties.getFileUpload() + FOLDER_NAME;
            String fileName = FileUtil.saveFile(file, folderPath);
            oldFileUrl = booth.getUrl();
            booth.setUrl(FOLDER_NAME + fileName);
        }
        if (boothDao.updateByPrimaryKeySelective(booth)>0) {
            if (oldFileUrl != null) {
                FileUtil.deleteFile(adminProperties.getFileUpload() + oldFileUrl);
            }
            return true;
        }
        return false;
    }

    @Override
    public Booth getById(Long id) {
        return boothDao.selectByPrimaryKey(id);
    }
}
