package com.vehicleassistancediary.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value = {"/index", "/"})
    private String index() {
        return "index";
    }

}
