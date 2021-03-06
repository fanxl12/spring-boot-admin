package com.fanxl.admin.rest;

import com.fanxl.admin.service.UserService;
import com.fanxl.admin.utils.ResultUtil;
import com.fanxl.admin.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/10 0010 13:20
 */
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ApiResponse get(@PathVariable String id) {
        return ResultUtil.success(userService.get(id));
    }

    @GetMapping("")
    public ApiResponse test() {
        return ResultUtil.success("ok");
    }


}
