package com.fanxl.admin.web;

import com.fanxl.admin.entity.User;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:36
 */
@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String list(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("list", list);
        return "user/userList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("action", "create");
        return "user/userForm";
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(User user, RedirectAttributes ra, Model model){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userService.create(user)) {
            ra.addFlashAttribute("msg", "创建成功");
            return "redirect:/user";
        } else {
            model.addAttribute("msg", "创建失败");
            model.addAttribute("user", user);
            model.addAttribute("action", "create");
            return "user/userForm";
        }
    }

    @GetMapping("update/{id}")
    public String updateForm(@PathVariable String id, Model model){
        User user = userService.get(id);
        model.addAttribute("user", user);
        model.addAttribute("action", "update");
        return "user/userForm";
    }


    @PostMapping(value = "/update", produces = MediaType.TEXT_HTML_VALUE)
    public String update(User user, RedirectAttributes ra, Model model){
        try {
            if (StringUtils.isEmpty(user.getOldPassword())) {
                throw new AdminException(10030, "请输入旧密码");
            }
            if (StringUtils.isEmpty(user.getNewPassword())) {
                throw new AdminException(10032, "新密码不能为空");
            }
            if (!passwordEncoder.matches(user.getOldPassword(), user.getPassword())) {
                throw new AdminException(10031, "旧密码错误");
            }
            user.setPassword(passwordEncoder.encode(user.getNewPassword()));
            if (userService.update(user)){
                ra.addFlashAttribute("msg", "更新成功");
                return "redirect:/user";
            }
            model.addAttribute("msg", "更新失败");
        } catch (AdminException e) {
            model.addAttribute("msg", e.getMessage());
        }
        model.addAttribute("user", user);
        model.addAttribute("action", "update");
        return "user/userForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes ra, @PathVariable String id){
        if (userService.delete(id)){
            ra.addFlashAttribute("msg", "删除成功");
        }else {
            ra.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/user/";
    }

}
