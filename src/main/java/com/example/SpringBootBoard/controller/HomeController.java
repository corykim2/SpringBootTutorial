package com.example.SpringBootBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@는 어노테이션 -> 그냥 컴파일러에 역할 알리미
@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
