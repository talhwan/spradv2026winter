package com.thc.sprbasic2025fall.dto;

import com.thc.sprbasic2025fall.domain.Postlike;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class PostlikeDto {


    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class LikedReqDto{
        Long postingId;
        Long userId;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class LikedResDto{
        Boolean liked;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ToggleResDto{
        Boolean liked;
        Integer countlike;
    }
    /**/

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto{
        Long postingId;
        Long userId;

        public Postlike toEntity(){
            return Postlike.of(getPostingId(), getUserId());
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
