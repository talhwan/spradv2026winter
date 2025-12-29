package com.thc.sprbasic2025fall.service;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    DefaultDto.CreateResDto create(BoardDto.CreateReqDto param);
    void update(BoardDto.UpdateReqDto param);
    void delete(BoardDto.UpdateReqDto param);
    BoardDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param);
}
