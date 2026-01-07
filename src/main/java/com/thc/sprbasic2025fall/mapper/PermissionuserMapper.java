package com.thc.sprbasic2025fall.mapper;

import com.thc.sprbasic2025fall.dto.PermissionuserDto;

import java.util.List;

public interface PermissionuserMapper {
	PermissionuserDto.DetailResDto detail(Long id);
	List<PermissionuserDto.DetailResDto> list(PermissionuserDto.ListReqDto param);
	List<PermissionuserDto.DetailResDto> pagedList(PermissionuserDto.PagedListReqDto param);
	int listCount(PermissionuserDto.PagedListReqDto param);
    List<PermissionuserDto.DetailResDto> scrollList(PermissionuserDto.ScrollListReqDto param);
}