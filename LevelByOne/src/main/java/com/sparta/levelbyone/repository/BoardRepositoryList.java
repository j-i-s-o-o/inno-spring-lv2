package com.sparta.levelbyone.repository;

import com.sparta.levelbyone.entity.Board;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "Board Repository")
public class BoardRepositoryList {
    List<Board> BoardList = new ArrayList<>();
    public List<Board> GetListBoard(){
        return BoardList;
    }

    public Board save(Board newBoard){
        // List에 게시글 추가
        log.error("Save BoardList");
        BoardList.add(newBoard);
        return newBoard;
    }

    public Board findById(long id){
        Board returnBoard = BoardList.stream()
                .filter(e -> e.getId() == id)
                .toList()
                .get(0);

        return returnBoard;
    }

    public Board update(Board board){

        int index = BoardList.indexOf(findById(board.getId()));

        BoardList.set(index, board);

        return board;
    }

    public boolean remove(Board board){

        return BoardList.remove(board);
    }
}