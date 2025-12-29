package com.thc.sprbasic2025fall.mapper;

import com.thc.sprbasic2025fall.dto.PostcmtDto;

import java.util.List;

public interface PostcmtMapper {
	PostcmtDto.DetailResDto detail(Long id);
	List<PostcmtDto.DetailResDto> list(PostcmtDto.ListReqDto param);
	List<PostcmtDto.DetailResDto> pagedList(PostcmtDto.PagedListReqDto param);
	int listCount(PostcmtDto.PagedListReqDto param);
    List<PostcmtDto.DetailResDto> scrollList(PostcmtDto.ScrollListReqDto param);
}