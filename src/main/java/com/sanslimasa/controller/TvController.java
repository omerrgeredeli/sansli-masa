package com.sanslimasa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TvController {

    @GetMapping("/tv")
    public String tv() {
        return "tv.html";
    }

    @GetMapping("/admin-panel")
    public String admin() {
        return "admin.html";
    }
}
