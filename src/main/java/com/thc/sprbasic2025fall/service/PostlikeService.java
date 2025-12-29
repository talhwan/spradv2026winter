package com.thc.sprbasic2025fall.service;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostlikeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostlikeService {
    PostlikeDto.LikedResDto liked(PostlikeDto.LikedReqDto param);
    PostlikeDto.ToggleResDto toggle(PostlikeDto.CreateReqDto param);
    /**/
    DefaultDto.CreateResDto create(PostlikeDto.CreateReqDto param);
    void update(PostlikeDto.UpdateReqDto param);
    void delete(PostlikeDto.UpdateReqDto param);
    PostlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PostlikeDto.DetailResDto> list(PostlikeDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PostlikeDto.PagedListReqDto param);
    List<PostlikeDto.DetailResDto> scrollList(PostlikeDto.ScrollListReqDto param);
}
