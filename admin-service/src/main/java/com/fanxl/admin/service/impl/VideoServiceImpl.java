package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.VideoDao;
import com.fanxl.admin.entity.Video;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.properties.AdminProperties;
import com.fanxl.admin.service.VideoService;
import com.fanxl.admin.utils.FileUtil;
import com.fanxl.admin.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/11 0011 17:58
 */
@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    private static final String FOLDER_NAME = "video/";

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private AdminProperties adminProperties;

    @Override
    public boolean saveList(MultipartFile multipartFile) {
        try {
            Video video = new Video();
            video.setName(multipartFile.getOriginalFilename());
            String fileName = FileUtil.saveFile(multipartFile, adminProperties.getFileUpload() + FOLDER_NAME);
            video.setUrl(FOLDER_NAME + fileName);
            return videoDao.insertSelective(video)>0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Video> getAll() {
        return videoDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        Video video = videoDao.selectByPrimaryKey(id);
        if (video == null) {
            throw new AdminException(ResultEnum.FILE_NOT_FOUND);
        }
        if (videoDao.deleteByPrimaryKey(id)>0) {
            return FileUtil.deleteFile(adminProperties.getFileUpload() + video.getUrl());
        }
        throw new AdminException(ResultEnum.FAIL);
    }

    @Override
    public List<FileVO> getVideo() {
        return videoDao.selectAll().stream().map(item -> {
            FileVO videoVO = new FileVO();
            BeanUtils.copyProperties(item, videoVO);
            return videoVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void download(HttpServletResponse res) {
        List<Video> videoList = getAll();
        if (videoList == null || videoList.size()==0) {
            throw new AdminException(10021, "未找到文件");
        }
        Video video = videoList.get(0);
        String fileName = video.getName();
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(adminProperties.getFileUpload() + video.getUrl())));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
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
        }
    }
}
