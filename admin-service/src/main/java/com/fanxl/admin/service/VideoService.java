package com.fanxl.admin.service;

import com.fanxl.admin.entity.Video;
import com.fanxl.admin.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface VideoService {

    /**
     * 保存多张图片
     * @param fileList
     * @return
     */
    boolean saveList(MultipartFile fileList);

    /**
     * 获取所有图片
     * @return
     */
    List<Video> getAll();

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 获取视频
     * @return
     */
    List<FileVO> getVideo();

    /**
     * 下载文件
     * @param res
     */
    void download(HttpServletResponse res);

}
