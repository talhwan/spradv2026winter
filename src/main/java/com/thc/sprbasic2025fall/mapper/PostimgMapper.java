package com.thc.sprbasic2025fall.mapper;

import com.thc.sprbasic2025fall.dto.PostimgDto;

import java.util.List;

public interface PostimgMapper {
	PostimgDto.DetailResDto detail(Long id);
	List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto param);
	List<PostimgDto.DetailResDto> pagedList(PostimgDto.PagedListReqDto param);
	int listCount(PostimgDto.PagedListReqDto param);
    List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto param);
}