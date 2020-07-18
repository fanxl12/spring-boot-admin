package com.fanxl.admin.web;

import com.fanxl.admin.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:36
 */
@RequestMapping("/plane")
@Controller
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("list", planeService.getAll());
        return "plane/planeList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("action", "create");
        return "plane/planeForm";
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(@RequestParam("file") MultipartFile file, RedirectAttributes ra, Model model){
        if (planeService.save(file)) {
            ra.addFlashAttribute("msg", "创建成功");
            return "redirect:/plane";
        } else {
            model.addAttribute("msg", "创建失败");
            model.addAttribute("action", "create");
            return "plane/planeForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes ra, @PathVariable(value = "id") Long id){
        if (planeService.delete(id)){
            ra.addFlashAttribute("msg", "删除成功");
        }else {
            ra.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/plane/";
    }
}
