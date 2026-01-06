package com.thc.sprbasic2025fall.service;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    DefaultDto.CreateResDto signup(UserDto.CreateReqDto param, Long reqUserId);
    /**/
    DefaultDto.CreateResDto create(UserDto.CreateReqDto param, Long reqUserId);
    void update(UserDto.UpdateReqDto param, Long reqUserId);
    void delete(UserDto.UpdateReqDto param, Long reqUserId);
    UserDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<UserDto.DetailResDto> list(UserDto.ListReqDto param, Long reqUserId);
    DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto param, Long reqUserId);
    List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto param, Long reqUserId);
}
