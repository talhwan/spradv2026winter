package com.thc.sprbasic2025fall.dto;

import com.thc.sprbasic2025fall.domain.Posting;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class PostingDto {

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto{
        Long userId;
        String title;
        String content;
        String img;

        List<String> imgs;

        public Posting toEntity(){
            return Posting.of(getUserId(), getTitle(), getContent(), getImg());
        }
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        String title;
        String content;
        String img;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long userId;
        String title;
        String content;
        String img;
        Integer countlike;

        String userUsername;
        String userName;
        String userNick;

        List<PostimgDto.DetailResDto> imgs;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ListReqDto extends DefaultDto.ListReqDto {
        Long userId;
        String title;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        Long userId;
        String title;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        Long userId;
        String title;
    }
}
