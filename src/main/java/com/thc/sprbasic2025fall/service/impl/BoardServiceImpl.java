package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.Board;
import com.thc.sprbasic2025fall.dto.BoardDto;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.repository.BoardRepository;
import com.thc.sprbasic2025fall.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    final BoardRepository boardRepository;

    @Override
    public DefaultDto.CreateResDto create(BoardDto.CreateReqDto param) {
        Board board = param.toEntity();
        board = boardRepository.save(board);

        DefaultDto.CreateResDto res = board.toCreateResDto();
        return res;
    }

    @Override
    public void update(BoardDto.UpdateReqDto param) {
        Board board = boardRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data found"));
        board.update(param);
        boardRepository.save(board);
    }

    @Override
    public void delete(BoardDto.UpdateReqDto param) {
        update(BoardDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    @Override
    public BoardDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        Board board = boardRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data found"));

        BoardDto.DetailResDto res = BoardDto.DetailResDto.builder()
                .id(board.getId())
                .deleted(board.getDeleted())
                .createdAt(board.getCreatedAt())
                .modifiedAt(board.getModifiedAt())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getAuthor())
                .build();
        return res;
    }

    @Override
    public List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param) {
        List<BoardDto.DetailResDto> final_list = new ArrayList<>();
        List<Board> list = boardRepository.findAll();
        for(Board each : list){
            boolean is_this = true;
            if(param.getDeleted() != null){
                if(each.getDeleted() != param.getDeleted()){
                    is_this = false;
                }
            }
            if(param.getTitle() != null){
                if(!each.getTitle().equals(param.getTitle())){
                    is_this = false;
                }
            }
            if(param.getAuthor() != null){
                if(!each.getAuthor().equals(param.getAuthor())){
                    is_this = false;
                }
            }
            if(is_this){
                final_list.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
            }
        }
        return final_list;
    }
}
