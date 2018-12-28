package com.fanxl.admin.web;

import com.fanxl.admin.entity.Category;
import com.fanxl.admin.service.CategoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:36
 */
@RequestMapping("category")
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String list(Model model, @PageableDefault(page = 1) Pageable pageable){
        PageInfo<Category> pageInfo = categoryService.findAll(pageable);
        model.addAttribute("pageInfo", pageInfo);
        return "category/categoryList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("action", "create");
        return "category/categoryForm";
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(Category category, RedirectAttributes ra, Model model){
        model.addAttribute("msg", "创建失败");
        model.addAttribute("category", category);
        model.addAttribute("action", "create");
        return "category/categoryForm";


//        if (categoryService.save(category)){
//            ra.addFlashAttribute("msg", "创建成功");
//            if (category.getParentId()!=null){
//                return "redirect:/category/"+category.getParentId();
//            }else {
//                return "redirect:/category";
//            }
//        }else {
//            model.addAttribute("msg", "创建失败");
//            model.addAttribute("category", category);
//            if (category.getParentId()!=null){
//                return "category/typeForm";
//            }else {
//                return "category/categoryForm";
//            }
//        }
    }

}
