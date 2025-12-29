package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.Postcmt;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostcmtDto;
import com.thc.sprbasic2025fall.mapper.PostcmtMapper;
import com.thc.sprbasic2025fall.repository.PostcmtRepository;
import com.thc.sprbasic2025fall.service.PostcmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostcmtServiceImpl implements PostcmtService {

    final PostcmtRepository postcmtRepository;
    final PostcmtMapper postcmtMapper;

    @Override
    public DefaultDto.CreateResDto create(PostcmtDto.CreateReqDto param) {
        return postcmtRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(PostcmtDto.UpdateReqDto param) {
        Postcmt postcmt = postcmtRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        postcmt.update(param);
        postcmtRepository.save(postcmt);
    }

    @Override
    public void delete(PostcmtDto.UpdateReqDto param) {
        update(PostcmtDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public PostcmtDto.DetailResDto get(DefaultDto.DetailReqDto param) {
        PostcmtDto.DetailResDto res = postcmtMapper.detail(param.getId());
        return res;
    }

    @Override
    public PostcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param);
    }

    public List<PostcmtDto.DetailResDto> addlist(List<PostcmtDto.DetailResDto> list) {
        List<PostcmtDto.DetailResDto> newList = new ArrayList<>();
        for (PostcmtDto.DetailResDto postcmt : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(postcmt.getId()).build()));
        }
        return newList;
    }

    @Override
    public List<PostcmtDto.DetailResDto> list(PostcmtDto.ListReqDto param) {
        List<PostcmtDto.DetailResDto> list = new ArrayList<>();
        List<PostcmtDto.DetailResDto> postcmts = postcmtMapper.list(param);
        return addlist(postcmts);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(PostcmtDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(postcmtMapper.listCount(param));
        res.setList(addlist(postcmtMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<PostcmtDto.DetailResDto> scrollList(PostcmtDto.ScrollListReqDto param) {
        return addlist(postcmtMapper.scrollList(param));
    }
}
