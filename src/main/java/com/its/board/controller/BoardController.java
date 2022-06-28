package com.its.board.controller;

import com.its.board.common.PagingConst;
import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save-form") //글작성 화면
    public String saveForm() {
        return "boardPages/save";
    }

    @PostMapping("/save") //글작성 처리
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException { // throws IOException 추가
        System.out.println(boardDTO);
        Long id = boardService.save(boardDTO);
        return "redirect:/board";
    }

    @GetMapping("/")  // 글목록
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "boardPages/list";
    }

    @GetMapping("/{id}") // 주소값의 id 값을 가져오는 작업
    public String findById(@PathVariable Long id, Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "boardPages/detail";
    }

    // 수정화면 요청
    @GetMapping("/update/{id}") // 수정화면 요청
    public String updateForm(@PathVariable Long id, Model model) {
        System.out.println(id);
        BoardDTO boardDTO = boardService.findById(id);
        System.out.println("boardDTO = " + boardDTO);
        model.addAttribute("boardUpdate", boardDTO);
        return "boardPages/update";
    }


    @PostMapping("/update")   // 수정처리
    public String update(@ModelAttribute BoardDTO boardDTO){
        boardService.update(boardDTO);
        return "redirect:/board/" + boardDTO.getId();
    }

    @GetMapping("/delete/{id}") // 글삭제
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/board";
    }

    // /board?page=1
    // /board/3/1
    // rest api: 주소값만으로 자원을 식별 /board/10

    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) { //페이지 처리
        Page<BoardDTO> boardList = boardService.paging(pageable);
        model.addAttribute("boardList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1; // 스타트 페이지
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages(); // 끝 페이지
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boardPages/paging";
    }

    @GetMapping("/search") //검색
    public String search(@RequestParam("q") String q, Model model){
        List<BoardDTO> searchList = boardService.search(q);
        model.addAttribute("searchList", searchList);
        return "boardPages/search";
    }
}












