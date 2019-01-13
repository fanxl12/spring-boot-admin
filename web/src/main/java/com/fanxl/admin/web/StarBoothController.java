package com.fanxl.admin.web;

import com.fanxl.admin.dto.StarBoothDTO;
import com.fanxl.admin.entity.StarBooth;
import com.fanxl.admin.service.CategoryService;
import com.fanxl.admin.service.StarBoothService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:36
 */
@RequestMapping("starBooth")
@Controller
public class StarBoothController {

    @Autowired
    private StarBoothService starBoothService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String list(Model model, @PageableDefault(page = 1) Pageable pageable){
        PageInfo<StarBoothDTO> pageInfo = starBoothService.getWebList(pageable);
        model.addAttribute("pageInfo", pageInfo);
        return "starBooth/starBoothList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("starBooth", new StarBooth());
        model.addAttribute("action", "create");
        getCommonData(model);
        return "starBooth/starBoothForm";
    }

    private void getCommonData(Model model) {
        model.addAttribute("categoryList", categoryService.getAll());
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(StarBooth starBooth, @RequestParam("file") MultipartFile file, RedirectAttributes ra, Model model){
        if (starBoothService.create(starBooth, file)) {
            ra.addFlashAttribute("msg", "创建成功");
            return "redirect:/starBooth";
        } else {
            model.addAttribute("msg", "创建失败");
            model.addAttribute("starBooth", starBooth);
            model.addAttribute("action", "create");
            return "starBooth/starBoothForm";
        }
    }

    @GetMapping("update/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model){
        StarBooth starBooth = starBoothService.getById(id);
        model.addAttribute("starBooth", starBooth);
        model.addAttribute("action", "update");
        getCommonData(model);
        return "starBooth/starBoothForm";
    }


    @PostMapping(value = "/update", produces = MediaType.TEXT_HTML_VALUE)
    public String update(StarBooth starBooth, BindingResult result,
                         RedirectAttributes ra, Model model){
        if (starBoothService.update(starBooth)){
            ra.addFlashAttribute("msg", "更新成功");
            return "redirect:/starBooth";
        }else {
            model.addAttribute("msg", "更新失败");
            model.addAttribute("starBooth", starBooth);
            model.addAttribute("action", "update");
            return "starBooth/starBoothForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes ra, @PathVariable(value = "id") Long id){
        if (starBoothService.delete(id)){
            ra.addFlashAttribute("msg", "删除成功");
        }else {
            ra.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/starBooth/";
    }

}
