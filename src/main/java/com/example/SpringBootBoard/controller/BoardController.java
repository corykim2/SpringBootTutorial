package com.example.SpringBootBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board") //먼저 board로 시작하는 애들을 이 컨트롤러가 받음. 이후 그 이하의 주소를 각 매핑값에 일치하는 매서드를 호출
public class BoardController {
    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }
}
