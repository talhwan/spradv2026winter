package com.thc.sprbasic2025fall.dto;

import com.thc.sprbasic2025fall.domain.Permissiondetail;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class PermissiondetailDto {


    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class ToggleReqDto{
        Long permissionId;
        String target;
        Integer func;
        Boolean flag; // 입력일지 삭제일지 여부! 입력이면 true!
    }

    /**/

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReqDto{
        Long permissionId;
        String target;
        Integer func;

        public Permissiondetail toEntity(){
            return Permissiondetail.of(getPermissionId(), getTarget(), getFunc());
        }
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        Long permissionId;
        String target;
        Integer func;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long permissionId;
        String target;
        Integer func;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ListReqDto extends DefaultDto.ListReqDto {
        Long permissionId;
        String target;
        Integer func;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        Long permissionId;
        String target;
        Integer func;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        Long permissionId;
        String target;
        Integer func;
    }
}
