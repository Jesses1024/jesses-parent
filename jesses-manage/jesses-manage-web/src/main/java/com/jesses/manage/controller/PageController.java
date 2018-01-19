package com.jesses.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 通用的页面跳转功能
 */
@Controller
@RequestMapping("page")
public class PageController {
    @RequestMapping(value = "{pageName}",method = RequestMethod.GET)
    public String toPage(@PathVariable("pageName")String pathname){
        return pathname;
    }
}
