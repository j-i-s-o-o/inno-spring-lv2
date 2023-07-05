package com.sparta.levelbyone.entity;

import com.sparta.levelbyone.dto.BoardModifiedDto;
import com.sparta.levelbyone.dto.BoardResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="board")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String title;
    private String contents;


    public Board(BoardModifiedDto requestDto, String username) {
        this.username = username;
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public BoardResponseDto update(BoardModifiedDto modifiedDto) {
        this.title = modifiedDto.getTitle();
        this.contents = modifiedDto.getContents();
        return null;
    }

}
