package com.fanxl.admin.rest;

import com.fanxl.admin.service.AdvertImageService;
import com.fanxl.admin.service.WheelPictureService;
import com.fanxl.admin.utils.ResultUtil;
import com.fanxl.admin.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/10 0010 13:20
 */
@RestController
@RequestMapping(value = "/api/v1/home")
public class HomeRestController {

    @Autowired
    private WheelPictureService wheelPictureService;

    @Autowired
    private AdvertImageService advertImageService;

    @GetMapping("wheel")
    public ApiResponse getWheel() {
        return ResultUtil.success(wheelPictureService.getWheel());
    }

    @GetMapping("advert")
    public ApiResponse advert() {
        return ResultUtil.success(advertImageService.getAdvertList());
    }


}
