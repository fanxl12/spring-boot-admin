package com.fanxl.admin.rest;

import com.fanxl.admin.service.VideoService;
import com.fanxl.admin.utils.ResultUtil;
import com.fanxl.admin.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/10 0010 13:20
 */
@RestController
@RequestMapping(value = "/api/v1/video")
public class VideoRestController {

    @Autowired
    private VideoService videoService;

    @GetMapping()
    public void get(HttpServletResponse res) {
        videoService.download(res);
    }






}
