package com.thc.sprbasic2025fall.dto;

import com.thc.sprbasic2025fall.domain.Postcmt;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class PostcmtDto {

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto{
        Long postingId;
        Long userId;
        String content;

        public Postcmt toEntity(){
            return Postcmt.of(getPostingId(), getUserId(), getContent());
        }
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        String content;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long postingId;
        Long userId;
        String content;

        String userUsername;
        String userName;
        String userNick;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ListReqDto extends DefaultDto.ListReqDto {
        Long postingId;
        Long userId;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        Long postingId;
        Long userId;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        Long postingId;
        Long userId;
    }
}
