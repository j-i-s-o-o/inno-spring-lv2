package com.sparta.levelbyone.Controller;

import com.sparta.levelbyone.dto.BoardModifiedDto;
import com.sparta.levelbyone.dto.BoardRequestDeleteDto;
import com.sparta.levelbyone.dto.BoardResponseDeleteDto;
import com.sparta.levelbyone.dto.BoardResponseDto;
import com.sparta.levelbyone.entity.Board;
import com.sparta.levelbyone.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j(topic = "Controller Log")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BoardController {

    BoardService boardService;

    @GetMapping("/boards")
    public List<BoardResponseDto> GetListBoard(){
        return boardService.GetListBoard();
    }
    @PostMapping("/board")
    public BoardResponseDto CreateBoard(@RequestBody BoardModifiedDto requestDto, HttpServletRequest request) {
        return boardService.CreateBoard(requestDto, request);
    }
    @GetMapping("/board/{id}")
    public BoardResponseDto SelectBoard(@PathVariable long id){
        return boardService.SelectBoard(id);
    }

    @PutMapping("/board/{id}")
    public Board SelectModifiedBoard(@PathVariable long id,
                                     @RequestBody BoardModifiedDto requestDto,
                                     HttpServletRequest request){
        return boardService.SelectModifiedBoard(id, requestDto, request);
    }

    @DeleteMapping("/board/{id}")
    public String SelectDeleteBoard(@PathVariable long id,
                                    HttpServletRequest request){
        boardService.SelectDeleteBoard(id, request);
        return "삭제 성공";
    }
}
