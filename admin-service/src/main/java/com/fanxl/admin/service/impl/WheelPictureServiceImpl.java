package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.WheelPictureDao;
import com.fanxl.admin.service.WheelPictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/11 0011 17:58
 */
@Slf4j
@Service
public class WheelPictureServiceImpl implements WheelPictureService {

    @Autowired
    private WheelPictureDao wheelPictureDao;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public boolean saveList(MultipartFile[] fileList) {

        Resource resource = resourceLoader.getResource("classpath:/static/images");

        try {
            String dd = resource.getFile().getAbsolutePath();
            log.info("文件存在：" + dd);
        } catch (IOException e) {
            e.printStackTrace();
        }



//        if (fileList == null || fileList.length == 0) {
//            throw new AdminException(ResultEnum.FILE_NOT_EMPTY);
//        }
//
//
//
//        for (MultipartFile multipartFile : fileList) {
//            String originalFilename = multipartFile.getOriginalFilename();
//            String fileType = FileUtil.getFileType(originalFilename);
//            String fileName = System.currentTimeMillis() + fileType;
//
//
//
//            File file = new File(fileName);
//            try {
//                multipartFile.transferTo(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return false;
    }
}
