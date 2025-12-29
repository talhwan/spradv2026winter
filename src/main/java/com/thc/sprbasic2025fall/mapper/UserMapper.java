package com.thc.sprbasic2025fall.mapper;

import com.thc.sprbasic2025fall.dto.UserDto;

import java.util.List;

public interface UserMapper {
	UserDto.DetailResDto detail(Long id);
	List<UserDto.DetailResDto> list(UserDto.ListReqDto param);
	List<UserDto.DetailResDto> pagedList(UserDto.PagedListReqDto param);
	int listCount(UserDto.PagedListReqDto param);
    List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto param);
}