package com.uni.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping(value = "/")
    public String startpage() {
        return "startpage";
    }
    @GetMapping(value = "/overview")
    public String overview() {
        return "overview";
    }
}
