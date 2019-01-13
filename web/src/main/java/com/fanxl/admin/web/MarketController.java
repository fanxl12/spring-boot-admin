package com.fanxl.admin.web;

import com.fanxl.admin.entity.Market;
import com.fanxl.admin.service.MarketService;
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
@RequestMapping("market")
@Controller
public class MarketController {

    @Autowired
    private MarketService marketService;

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("list", marketService.getAll());
        return "market/marketList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("market", new Market());
        model.addAttribute("action", "create");
        return "market/marketForm";
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(Market market, RedirectAttributes ra, Model model){
        if (marketService.create(market)) {
            ra.addFlashAttribute("msg", "创建成功");
            return "redirect:/market";
        } else {
            model.addAttribute("msg", "创建失败");
            model.addAttribute("market", market);
            model.addAttribute("action", "create");
            return "market/marketForm";
        }
    }

    @GetMapping("update/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model){
        Market market = marketService.getById(id);
        model.addAttribute("market", market);
        model.addAttribute("action", "update");
        return "market/marketForm";
    }


    @PostMapping(value = "/update", produces = MediaType.TEXT_HTML_VALUE)
    public String update(Market market, BindingResult result,
                         RedirectAttributes ra, Model model){
        if (marketService.update(market)){
            ra.addFlashAttribute("msg", "更新成功");
            return "redirect:/market";
        }else {
            model.addAttribute("msg", "更新失败");
            model.addAttribute("market", market);
            model.addAttribute("action", "update");
            return "market/marketForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes ra, @PathVariable(value = "id") Long id){
        if (marketService.delete(id)){
            ra.addFlashAttribute("msg", "删除成功");
        }else {
            ra.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/market/";
    }

}
