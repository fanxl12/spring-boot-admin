package com.fanxl.admin.web;

import com.fanxl.admin.entity.MarketRegion;
import com.fanxl.admin.service.MarketRegionService;
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
@RequestMapping("marketRegion")
@Controller
public class MarketRegionController {

    @Autowired
    private MarketRegionService marketRegionService;

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("list", marketRegionService.getAll());
        return "marketRegion/marketRegionList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("marketRegion", new MarketRegion());
        model.addAttribute("action", "create");
        return "marketRegion/marketRegionForm";
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(MarketRegion marketRegion, RedirectAttributes ra, Model model){
        if (marketRegionService.create(marketRegion)) {
            ra.addFlashAttribute("msg", "创建成功");
            return "redirect:/marketRegion";
        } else {
            model.addAttribute("msg", "创建失败");
            model.addAttribute("marketRegion", marketRegion);
            model.addAttribute("action", "create");
            return "marketRegion/marketRegionForm";
        }
    }

    @GetMapping("update/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model){
        MarketRegion marketRegion = marketRegionService.getById(id);
        model.addAttribute("marketRegion", marketRegion);
        model.addAttribute("action", "update");
        return "marketRegion/marketRegionForm";
    }


    @PostMapping(value = "/update", produces = MediaType.TEXT_HTML_VALUE)
    public String update(MarketRegion marketRegion, BindingResult result,
                         RedirectAttributes ra, Model model){
        if (marketRegionService.update(marketRegion)){
            ra.addFlashAttribute("msg", "更新成功");
            return "redirect:/marketRegion";
        }else {
            model.addAttribute("msg", "更新失败");
            model.addAttribute("marketRegion", marketRegion);
            model.addAttribute("action", "update");
            return "marketRegion/marketRegionForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes ra, @PathVariable(value = "id") Long id){
        if (marketRegionService.delete(id)){
            ra.addFlashAttribute("msg", "删除成功");
        }else {
            ra.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/marketRegion/";
    }

}
