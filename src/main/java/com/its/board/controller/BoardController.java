package com.its.board.controller;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("save-form") //글작성 화면
    public String saveForm(){
        return "boardPages/save";
    }

    @PostMapping("/save") //글작성 처리
    public String save(@ModelAttribute BoardDTO boardDTO){
        Long id = boardService.save(boardDTO);
        return "redirect:/board/"+id;
    }
    @GetMapping("/{id}") // 주소값의 id 값을 가져오는 작업
    public String findById(@PathVariable Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board",boardDTO);
        return "boardPages/detail";
    }

    @GetMapping("/") // 글목록
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "boardPages/list";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model ){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("findDTO",boardDTO);
        return "boardPages/update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO){
        boardService.update(boardDTO);
        return "redirect:/board" + boardDTO.getId();
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/";
    }

}
