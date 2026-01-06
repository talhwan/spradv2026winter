package com.thc.sprbasic2025fall.mapper;

import com.thc.sprbasic2025fall.dto.PermissionDto;

import java.util.List;

public interface PermissionMapper {
	PermissionDto.DetailResDto detail(Long id);
	List<PermissionDto.DetailResDto> list(PermissionDto.ListReqDto param);
	List<PermissionDto.DetailResDto> pagedList(PermissionDto.PagedListReqDto param);
	int listCount(PermissionDto.PagedListReqDto param);
    List<PermissionDto.DetailResDto> scrollList(PermissionDto.ScrollListReqDto param);
}