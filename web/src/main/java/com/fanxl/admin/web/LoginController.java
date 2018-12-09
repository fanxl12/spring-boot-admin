package com.fanxl.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/8 0008 22:58
 */
@RequestMapping("login")
@Controller
public class LoginController {

    @GetMapping("")
    public String getLogin() {
        return "login";
    }

    @PostMapping("")
    public String login(Model model, @RequestParam String username,
                        @RequestParam String password, RedirectAttributes ra) {
        if (username.equals("fanxl12")) {
            if (password.equals("123")) {
                return "redirect:/index";
            } else {
                ra.addFlashAttribute("msg", "密码错误");
                model.addAttribute("msg", "密码错误了");
            }
        } else {
            ra.addFlashAttribute("msg", "用户名错误");
            model.addAttribute("msg", "用户名错误了");
        }
        return "login";
    }

}
