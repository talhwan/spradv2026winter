package com.thc.sprbasic2025fall.service;

import com.thc.sprbasic2025fall.domain.Posting;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostingDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PostingService {
    DefaultDto.CreateResDto create(PostingDto.CreateReqDto param, Long reqUserId);
    void update(PostingDto.UpdateReqDto param, Long reqUserId);
    void delete(PostingDto.UpdateReqDto param, Long reqUserId);
    PostingDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<PostingDto.DetailResDto> list(PostingDto.ListReqDto param, Long reqUserId);
    DefaultDto.PagedListResDto pagedList(PostingDto.PagedListReqDto param, Long reqUserId);
    List<PostingDto.DetailResDto> scrollList(PostingDto.ScrollListReqDto param, Long reqUserId);
}
