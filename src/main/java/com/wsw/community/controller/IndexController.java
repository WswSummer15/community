package com.wsw.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(HttpServletRequest request){

        return "index";
    }
}
