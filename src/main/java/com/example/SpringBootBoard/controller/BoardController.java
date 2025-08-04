package com.example.SpringBootBoard.controller;

import com.example.SpringBootBoard.dto.BoardDTO;
import com.example.SpringBootBoard.dto.UpdateDTO;
import com.example.SpringBootBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public String findAll(Model model) { //컨트롤러에서 데이터를 model에 담아서 View로 넘겨주면, HTML에서 그 데이터를 사용 가능
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList); //Thymeleaf(html 페이지에 for문 걸고 이런거) 쓰니까 가능한 방식임.
        //그거 안쓰면 json을 ajax로 받아서 쓰는 rest방식을 사용해야 함.
        //타임리프는 뷰 템플릿 엔진이고, model을 ViewResolver가 각 뷰 엔진에 맞는 형태로 바꿔서 보내줌. 그 엔진은 의존성에서 파악
        //참고로 React는 js로 사용자의 화면에 뭘 그리는 방식이라서 json 형식으로 api를 쏴주면 그걸 프론트가 처리하는 거임.
        //타임리프는 백엔드 하는 애가 좀 프론트까지 대충 한번에 하기 좋은 그런 방식임.
        return "list";
    }

    //게시글 상세조회는 2가지 작업이 필요. 해당 게시물의 조회수를 하나 올리고, 게시글 데이터를 가져와서 출력
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){ // @PathVariable은 URL 경로에 있는 값을 메서드의 매개변수로 바인딩, 변수명과 같으면 자동 아니면
        //@PathVariable("teamId") Long tId,이런 식으로도 가능함
        //참고로 Model은 인터페이스임
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute UpdateDTO updateDTO){
        int updateCheck = boardService.update(updateDTO);
        if(updateCheck==1) {
            return "redirect:/board/" + updateDTO.getId();
        }
        else{
            return null;
            //이건 어케 처리할지 생각이 안나서 비워둠.
        }
    }
}
