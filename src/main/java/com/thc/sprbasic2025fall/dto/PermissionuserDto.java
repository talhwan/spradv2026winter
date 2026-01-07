package com.thc.sprbasic2025fall.dto;

import com.thc.sprbasic2025fall.domain.Permissionuser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class PermissionuserDto {

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto{
        Long permissionId;
        Long userId;

        public Permissionuser toEntity(){
            return Permissionuser.of(getPermissionId(), getUserId());
        }
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        Long permissionId;
        Long userId;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class DetailResDto extends DefaultDto.DetailResDto {
        Long permissionId;
        Long userId;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ListReqDto extends DefaultDto.ListReqDto {
        Long permissionId;
        Long userId;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        Long permissionId;
        Long userId;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        Long permissionId;
        Long userId;
    }
}
