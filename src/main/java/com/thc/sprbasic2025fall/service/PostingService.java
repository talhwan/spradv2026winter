package com.thc.sprbasic2025fall.service;

import com.thc.sprbasic2025fall.domain.Posting;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostingDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PostingService {
    DefaultDto.CreateResDto create(PostingDto.CreateReqDto param);
    void update(PostingDto.UpdateReqDto param);
    void delete(PostingDto.UpdateReqDto param);
    PostingDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PostingDto.DetailResDto> list(PostingDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PostingDto.PagedListReqDto param);
    List<PostingDto.DetailResDto> scrollList(PostingDto.ScrollListReqDto param);
}
