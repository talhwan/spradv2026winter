package com.thc.sprbasic2025fall.domain;

import com.thc.sprbasic2025fall.dto.BoardDto;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board extends AuditingFileds{
    String title;
    String content;
    String author;

    protected Board(){}
    private Board(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public static Board of(String title, String content, String author) {
        return new Board(title, content, author);
    }
    public void update(BoardDto.UpdateReqDto param){
        if(param.getDeleted() != null){ setDeleted(param.getDeleted());}
        if(param.getTitle() != null){ setTitle(param.getTitle());}
        if(param.getContent() != null){ setContent(param.getContent());}
    }

    public DefaultDto.CreateResDto toCreateResDto(){
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}
