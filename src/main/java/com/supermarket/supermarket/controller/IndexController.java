package com.supermarket.supermarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/config")
    public String configPage(){
        return "config";
    }

}
