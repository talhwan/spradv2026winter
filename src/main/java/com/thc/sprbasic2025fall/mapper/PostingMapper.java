package com.thc.sprbasic2025fall.mapper;

import com.thc.sprbasic2025fall.dto.PostingDto;

import java.util.List;

public interface PostingMapper {
	PostingDto.DetailResDto detail(Long id);
	List<PostingDto.DetailResDto> list(PostingDto.ListReqDto param);
	List<PostingDto.DetailResDto> pagedList(PostingDto.PagedListReqDto param);
	int listCount(PostingDto.PagedListReqDto param);
    List<PostingDto.DetailResDto> scrollList(PostingDto.ScrollListReqDto param);
}