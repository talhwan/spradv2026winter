package com.thc.sprbasic2025fall.dto;

import com.thc.sprbasic2025fall.domain.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class UserDto {


    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class LoginResDto{
        String refreshToken;
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class LoginReqDto{
        String username;
        String password;
    }

    /**/

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto{
        String username;
        String password;
        String name;
        String nick;
        String phone;
        String birth;
        String gender;

        //CreateReqDto를 사용해서, User Entity 인스턴스 하나 만들어주세요!
        public User toEntity(){
            return User.of(getUsername(),getPassword(),getName(), getNick(),getPhone(),getBirth(),getGender());
        }
    }

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        String password;
        String name;
        String nick;
        String phone;
        String birth;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class DetailResDto extends DefaultDto.DetailResDto {
        String username;
        String name;
        String nick;
        String phone;
        String birth;
        String gender;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ListReqDto extends DefaultDto.ListReqDto {
        String phone;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto {
        String phone;
    }
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto {
        String phone;
    }
}
