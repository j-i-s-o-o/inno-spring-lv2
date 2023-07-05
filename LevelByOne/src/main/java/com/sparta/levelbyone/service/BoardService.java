package com.sparta.levelbyone.service;


import com.sparta.levelbyone.dto.BoardModifiedDto;
import com.sparta.levelbyone.dto.BoardRequestDeleteDto;
import com.sparta.levelbyone.dto.BoardResponseDeleteDto;
import com.sparta.levelbyone.dto.BoardResponseDto;
import com.sparta.levelbyone.entity.Board;
import com.sparta.levelbyone.jwt.JwtUtil;
import com.sparta.levelbyone.repository.BoardRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    private final JwtUtil jwtUtil;

    BoardRepository boardRepository;

    public List<BoardResponseDto> GetListBoard(){
        return boardRepository.findAllByOrderByCreatedAtDesc()
                .stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto CreateBoard(BoardModifiedDto requestDto, HttpServletRequest request){
        String token = jwtUtil.getTokenFromRequest(request);
        String substringToken = jwtUtil.substringToken(token);

        boolean isToken = jwtUtil.validateToken(substringToken);
        if(!isToken) {
            return null;
        }

        String username = jwtUtil.getUserInfoFromToken(substringToken).getSubject();

        Board newBoard = new Board(requestDto, username);
        newBoard = boardRepository.save(newBoard);

        // 게시글 반환
        return new BoardResponseDto(newBoard);
    }

    public BoardResponseDto SelectBoard(long id){
        // 선택한 게시글 조회

        // DB에서 검색
        Board returnBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(("Not Find!")));

        // 게시글 반환
        return new BoardResponseDto(returnBoard);
    }

    @Transactional
    public Board SelectModifiedBoard(long id, BoardModifiedDto requestDto, HttpServletRequest request){
        // 선택한 게시글 수정
        String token = jwtUtil.getTokenFromRequest(request);
        String substringToken = jwtUtil.substringToken(token);

        boolean isToken = jwtUtil.validateToken(substringToken);
        if(!isToken) {
            return null;
        }

        String username = jwtUtil.getUserInfoFromToken(substringToken).getSubject();

        // DB에서 검색
        Board returnBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(("Not Find!")));

        if (returnBoard.getUsername().equals(username))
        {
            // 수정하는 내용
            returnBoard.update(requestDto);
            return returnBoard;
        }
        else
            return null;
    }

    public void SelectDeleteBoard(long id, HttpServletRequest request){
        String token = jwtUtil.getTokenFromRequest(request);
        String substringToken = jwtUtil.substringToken(token);

        boolean isToken = jwtUtil.validateToken(substringToken);
        if(!isToken) {
            throw new IllegalArgumentException("token error");
        }

        String username = jwtUtil.getUserInfoFromToken(substringToken).getSubject();

        // DB에서 검색
        Board returnBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(("Not Find!")));
//        BoardResponseDeleteDto response;
        if (returnBoard.getUsername().equals(username))
        {
            // 삭제 메세지
            boardRepository.delete(returnBoard);
//            response = new BoardResponseDeleteDto("삭제 됐어!!");
        }
//        else
//            response = new BoardResponseDeleteDto("삭제 안 됐어");

        // 게시글 반환
//        return response;
    }
}