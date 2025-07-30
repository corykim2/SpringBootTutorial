package com.example.SpringBootBoard.controller;

import com.example.SpringBootBoard.dto.BoardDTO;
import com.example.SpringBootBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor // final로 선언된 필드만을 파라미터로 받는 생성자를 자동으로 만들어주는 롬복 어노테이션
// 서비스/리포지토리 등 의존성 주입(DI)**을 편리하게 할 때 사용 -> boardService 생성자 만들어주는 거임
@RequestMapping("/board") //먼저 board로 시작하는 애들을 이 컨트롤러가 받음. 이후 그 이하의 주소를 각 매핑값에 일치하는 매서드를 호출
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) { //여기서 @ModelAttribute 없이 구현하려면 객체 만들고 @RequestParam로 받아서 하나씩 세터로 넣어줘여함
        boardService.save(boardDTO);
        return "index";
    }
}
