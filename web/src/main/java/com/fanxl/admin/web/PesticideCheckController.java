package com.fanxl.admin.web;

import com.fanxl.admin.entity.PesticideCheck;
import com.fanxl.admin.service.PesticideCheckService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 19:36
 */
@RequestMapping("pesticideCheck")
@Controller
public class PesticideCheckController {

    @Autowired
    private PesticideCheckService pesticideCheckService;

    @GetMapping("")
    public String list(Model model, @PageableDefault(page = 1) Pageable pageable){
        PageInfo<PesticideCheck> pageInfo = pesticideCheckService.getList(pageable);
        model.addAttribute("pageInfo", pageInfo);
        return "pesticideCheck/pesticideCheckList";
    }
}
