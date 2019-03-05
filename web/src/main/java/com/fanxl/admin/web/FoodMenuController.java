package com.fanxl.admin.web;

import com.fanxl.admin.entity.FoodMenu;
import com.fanxl.admin.service.FoodMenuCategoryService;
import com.fanxl.admin.service.FoodMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:36
 */
@RequestMapping("foodMenu")
@Controller
public class FoodMenuController {

    @Autowired
    private FoodMenuService foodMenuService;

    @Autowired
    private FoodMenuCategoryService foodMenuCategoryService;

    @GetMapping("")
    public String list(Model model){
        List<FoodMenu> list = foodMenuService.getAll();
        model.addAttribute("list", list);
        return "foodMenu/foodMenuList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        FoodMenu foodMenu = new FoodMenu();
        foodMenu.setPopular(false);
        model.addAttribute("foodMenu", foodMenu);
        model.addAttribute("action", "create");
        getCommonData(model);
        return "foodMenu/foodMenuForm";
    }

    private void getCommonData(Model model) {
        model.addAttribute("categoryList", foodMenuCategoryService.getAll());
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(FoodMenu foodMenu, @RequestParam("file") MultipartFile file,
                         RedirectAttributes ra, Model model){

        if (foodMenuService.create(foodMenu, file)) {
            ra.addFlashAttribute("msg", "创建成功");
            return "redirect:/foodMenu";
        } else {
            model.addAttribute("msg", "创建失败");
            model.addAttribute("foodMenu", foodMenu);
            model.addAttribute("action", "create");
            return "foodMenu/foodMenuForm";
        }
    }

    @GetMapping("update/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model){
        FoodMenu foodMenu = foodMenuService.getById(id);
        model.addAttribute("foodMenu", foodMenu);
        model.addAttribute("action", "update");
        getCommonData(model);
        return "foodMenu/foodMenuForm";
    }


    @PostMapping(value = "/update", produces = MediaType.TEXT_HTML_VALUE)
    public String update(FoodMenu foodMenu, @RequestParam(value = "file", required = false) MultipartFile file, RedirectAttributes ra, Model model){
        if (foodMenuService.update(foodMenu, file)){
            ra.addFlashAttribute("msg", "更新成功");
            return "redirect:/foodMenu";
        }else {
            model.addAttribute("msg", "更新失败");
            model.addAttribute("foodMenu", foodMenu);
            model.addAttribute("action", "update");
            return "foodMenu/foodMenuForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes ra, @PathVariable(value = "id") Long id){
        if (foodMenuService.delete(id)){
            ra.addFlashAttribute("msg", "删除成功");
        }else {
            ra.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/foodMenu/";
    }

}
