package com.sparta.levelbyone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardModifiedDto {
    private String title;
    private String contents;

    public BoardModifiedDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}


