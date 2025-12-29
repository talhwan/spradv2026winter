package com.thc.sprbasic2025fall.service;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostcmtDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostcmtService {
    DefaultDto.CreateResDto create(PostcmtDto.CreateReqDto param);
    void update(PostcmtDto.UpdateReqDto param);
    void delete(PostcmtDto.UpdateReqDto param);
    PostcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<PostcmtDto.DetailResDto> list(PostcmtDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(PostcmtDto.PagedListReqDto param);
    List<PostcmtDto.DetailResDto> scrollList(PostcmtDto.ScrollListReqDto param);
}
