package com.fanxl.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/8 0008 22:58
 */
@RequestMapping("login")
@Controller
public class LoginController {

    @GetMapping("")
    public String getLogin(Model model, @RequestParam(required = false) String error,
                           @RequestParam(required = false) String username) {
        model.addAttribute("msg", error);
        model.addAttribute("username", username);
        return "login";
    }
}
