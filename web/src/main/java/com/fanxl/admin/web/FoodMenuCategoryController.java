package com.fanxl.admin.web;

import com.fanxl.admin.entity.FoodMenuCategory;
import com.fanxl.admin.service.FoodMenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
@RequestMapping("foodMenuCategory")
@Controller
public class FoodMenuCategoryController {

    @Autowired
    private FoodMenuCategoryService foodMenuCategoryService;

    @GetMapping("")
    public String list(Model model){
        List<FoodMenuCategory> list = foodMenuCategoryService.getAll();
        model.addAttribute("list", list);
        return "foodMenuCategory/foodMenuCategoryList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("foodMenuCategory", new FoodMenuCategory());
        model.addAttribute("action", "create");
        return "foodMenuCategory/foodMenuCategoryForm";
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(FoodMenuCategory foodMenuCategory, RedirectAttributes ra, Model model){

        if (foodMenuCategoryService.create(foodMenuCategory)) {
            ra.addFlashAttribute("msg", "创建成功");
            return "redirect:/foodMenuCategory";
        } else {
            model.addAttribute("msg", "创建失败");
            model.addAttribute("foodMenuCategory", foodMenuCategory);
            model.addAttribute("action", "create");
            return "foodMenuCategory/foodMenuCategoryForm";
        }
    }

    @GetMapping("update/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model){
        FoodMenuCategory foodMenuCategory = foodMenuCategoryService.getById(id);
        model.addAttribute("foodMenuCategory", foodMenuCategory);
        model.addAttribute("action", "update");
        return "foodMenuCategory/foodMenuCategoryForm";
    }


    @PostMapping(value = "/update", produces = MediaType.TEXT_HTML_VALUE)
    public String update(FoodMenuCategory foodMenuCategory, BindingResult result,
                         RedirectAttributes ra, Model model){
        if (foodMenuCategoryService.update(foodMenuCategory)){
            ra.addFlashAttribute("msg", "更新成功");
            return "redirect:/foodMenuCategory";
        }else {
            model.addAttribute("msg", "更新失败");
            model.addAttribute("foodMenuCategory", foodMenuCategory);
            model.addAttribute("action", "update");
            return "foodMenuCategory/foodMenuCategoryForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes ra, @PathVariable(value = "id") Long id){
        if (foodMenuCategoryService.delete(id)){
            ra.addFlashAttribute("msg", "删除成功");
        }else {
            ra.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/foodMenuCategory/";
    }

}
