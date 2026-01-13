package com.example.OrderProject_MVC.controller.ThymeleafController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllerTL {
    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
