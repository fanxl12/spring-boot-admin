package com.fanxl.admin.web;

import com.fanxl.admin.entity.Market;
import com.fanxl.admin.service.ImportService;
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
@RequestMapping("import")
@Controller
public class ImportController {

    @Autowired
    private ImportService importService;

    @GetMapping("/{action}")
    public String createForm(Model model, @PathVariable String action){
        model.addAttribute("market", new Market());
        model.addAttribute("action", action);
        return "import/importForm";
    }

    @PostMapping(value = "/{action}", produces = MediaType.TEXT_HTML_VALUE)
    public String create(@RequestParam("file") MultipartFile file, @PathVariable String action,
                         RedirectAttributes ra, Model model){
        if (file == null || file.isEmpty()) {
            model.addAttribute("msg", "文件为空，请上传文件");
            model.addAttribute("action", action);
            return "import/importForm";
        }
        if (importService.create(action, file)) {
            ra.addFlashAttribute("msg", "导入成功");
            return "redirect:/" + action;
        } else {
            model.addAttribute("msg", "导入失败");
            model.addAttribute("action", action);
            return "import/importForm";
        }
    }
}
