package com.fanxl.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/8 0008 22:58
 */
@RequestMapping("test")
@Controller
public class TestController {

    @GetMapping("")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("test");
        mv.addObject("title", "你好");
        return mv;
    }

}
