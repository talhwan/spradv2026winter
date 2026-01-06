package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.Posting;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostingDto;
import com.thc.sprbasic2025fall.mapper.PostingMapper;
import com.thc.sprbasic2025fall.repository.PostingRepository;
import com.thc.sprbasic2025fall.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostingServiceImpl implements PostingService {

    final PostingRepository postingRepository;
    final PostingMapper postingMapper;

    @Override
    public DefaultDto.CreateResDto create(PostingDto.CreateReqDto param, Long reqUserId) {
        param.setUserId(reqUserId);
        DefaultDto.CreateResDto res = postingRepository.save(param.toEntity()).toCreateResDto();
        return res;
    }

    @Override
    public void update(PostingDto.UpdateReqDto param, Long reqUserId) {
        Posting posting = postingRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if(!reqUserId.equals(posting.getUserId())) {
            throw new RuntimeException("you don't have permission to update posting");
        }
        posting.update(param);
        postingRepository.save(posting);

    }

    @Override
    public void delete(PostingDto.UpdateReqDto param, Long reqUserId) {
        update(PostingDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    public PostingDto.DetailResDto get(DefaultDto.DetailReqDto param) {
        PostingDto.DetailResDto res = postingMapper.detail(param.getId());
        return res;
    }

    @Override
    public PostingDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param);
    }

    public List<PostingDto.DetailResDto> addlist(List<PostingDto.DetailResDto> list, Long reqUserId) {
        List<PostingDto.DetailResDto> newList = new ArrayList<>();
        for (PostingDto.DetailResDto posting : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(posting.getId()).build()));
        }
        return newList;
    }

    @Override
    public List<PostingDto.DetailResDto> list(PostingDto.ListReqDto param, Long reqUserId) {
        List<PostingDto.DetailResDto> list = new ArrayList<>();
        List<PostingDto.DetailResDto> postings = postingMapper.list(param);
        return addlist(postings, reqUserId);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(PostingDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(postingMapper.listCount(param));
        res.setList(addlist(postingMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<PostingDto.DetailResDto> scrollList(PostingDto.ScrollListReqDto param, Long reqUserId) {
        return addlist(postingMapper.scrollList(param), reqUserId);
    }
}
