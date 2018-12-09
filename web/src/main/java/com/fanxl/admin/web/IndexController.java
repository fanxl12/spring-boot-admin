package com.fanxl.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/8 0008 22:58
 */
@RequestMapping("index")
@Controller
public class IndexController {

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("username", "fanxl10");
        model.addAttribute("head", "http://t.cn/RCzsdCq");
        return "index";
    }
}
