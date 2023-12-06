package ru.pleshkova.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/main")
    public String mainPage(){
        return "main/main";
    }

    @GetMapping("/mirror")
    public String mirror(@RequestParam(value = "key", required = false) String key, Model model) {
        model.addAttribute("key", key);
        return "main/mirror";
    }
}
