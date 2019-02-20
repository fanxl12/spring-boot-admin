package com.fanxl.admin.web;

import com.fanxl.admin.dto.BoothDTO;
import com.fanxl.admin.entity.Booth;
import com.fanxl.admin.service.BoothService;
import com.fanxl.admin.service.CategoryService;
import com.fanxl.admin.service.MarketRegionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("booth")
@Controller
public class BoothController {

    @Autowired
    private BoothService boothService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MarketRegionService marketRegionService;

    @GetMapping("")
    public String list(Model model, @PageableDefault(page = 1) Pageable pageable){
        PageInfo<BoothDTO> pageInfo = boothService.getWebList(pageable);
        model.addAttribute("pageInfo", pageInfo);
        return "booth/boothList";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("booth", new Booth());
        model.addAttribute("action", "create");
        getCommonData(model);
        return "booth/boothForm";
    }

    private void getCommonData(Model model) {
        model.addAttribute("categoryList", categoryService.getAll());
        model.addAttribute("marketRegionList", marketRegionService.getByMarkId(1L));
    }

    @PostMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String create(Booth booth, @RequestParam("file") MultipartFile file,
                         @RequestParam(value = "businessLicenseFile", required = false) MultipartFile businessLicense,
                         @RequestParam(value = "licenseFile", required = false) MultipartFile license,
                         @RequestParam(value = "heathLicenseFile", required = false) MultipartFile heathLicense,
                         RedirectAttributes ra, Model model) throws Exception{
        if (boothService.create(booth, file, businessLicense, license, heathLicense)) {
            ra.addFlashAttribute("msg", "创建成功");
            return "redirect:/booth";
        } else {
            model.addAttribute("msg", "创建失败");
            model.addAttribute("booth", booth);
            model.addAttribute("action", "create");
            return "booth/boothForm";
        }
    }

    @GetMapping("update/{id}")
    public String updateForm(@PathVariable(value = "id") Long id, Model model){
        Booth booth = boothService.getById(id);
        model.addAttribute("booth", booth);
        model.addAttribute("action", "update");
        getCommonData(model);
        return "booth/boothForm";
    }


    @PostMapping(value = "/update", produces = MediaType.TEXT_HTML_VALUE)
    public String update(Booth booth, @RequestParam(value = "file", required = false) MultipartFile file,
                         RedirectAttributes ra, Model model) throws Exception{
        if (boothService.update(booth, file)){
            ra.addFlashAttribute("msg", "更新成功");
            return "redirect:/booth";
        }else {
            model.addAttribute("msg", "更新失败");
            model.addAttribute("booth", booth);
            model.addAttribute("action", "update");
            return "booth/boothForm";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(RedirectAttributes ra, @PathVariable(value = "id") Long id){
        if (boothService.delete(id)){
            ra.addFlashAttribute("msg", "删除成功");
        }else {
            ra.addFlashAttribute("msg", "删除失败");
        }
        return "redirect:/booth/";
    }

}
