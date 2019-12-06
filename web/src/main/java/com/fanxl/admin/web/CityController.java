package com.fanxl.admin.web;

import com.fanxl.admin.entity.City;
import com.fanxl.admin.service.CityService;
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

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:36
 */
@RequestMapping("city")
@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("list", cityService.getAll());
        return "city/cityList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("city", new City());
        model.addAttribute("action", "create");
        return "city/cityForm";
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(City city, RedirectAttributes ra, Model model){
        if (cityService.create(city)) {
            ra.addFlashAttribute("msg", "创建成功");
            return "redirect:/city";
        } else {
            model.addAttribute("msg", "创建失败");
            model.addAttribute("city", city);
            model.addAttribute("action", "create");
            return "city/cityForm";
        }
    }

    @GetMapping("update/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model){
        City city = cityService.getById(id);
        model.addAttribute("city", city);
        model.addAttribute("action", "update");
        return "city/cityForm";
    }


    @PostMapping(value = "/update", produces = MediaType.TEXT_HTML_VALUE)
    public String update(City city, BindingResult result,
                         RedirectAttributes ra, Model model){
        if (cityService.update(city)){
            ra.addFlashAttribute("msg", "更新成功");
            return "redirect:/city";
        }else {
            model.addAttribute("msg", "更新失败");
            model.addAttribute("city", city);
            model.addAttribute("action", "update");
            return "city/cityForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes ra, @PathVariable(value = "id") Long id){
        if (cityService.delete(id)){
            ra.addFlashAttribute("msg", "删除成功");
        }else {
            ra.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/city/";
    }

}
