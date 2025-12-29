package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.Postimg;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostimgDto;
import com.thc.sprbasic2025fall.mapper.PostimgMapper;
import com.thc.sprbasic2025fall.repository.PostimgRepository;
import com.thc.sprbasic2025fall.service.PostimgService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostimgServiceImpl implements PostimgService {

    final PostimgRepository postimgRepository;
    final PostimgMapper postimgMapper;

    @Override
    public DefaultDto.CreateResDto create(PostimgDto.CreateReqDto param) {
        return postimgRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(PostimgDto.UpdateReqDto param) {
        Postimg postimg = postimgRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        postimg.update(param);
        postimgRepository.save(postimg);
    }

    @Override
    public void delete(PostimgDto.UpdateReqDto param) {
        update(PostimgDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public PostimgDto.DetailResDto get(DefaultDto.DetailReqDto param) {
        PostimgDto.DetailResDto res = postimgMapper.detail(param.getId());
        return res;
    }

    @Override
    public PostimgDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param);
    }

    public List<PostimgDto.DetailResDto> addlist(List<PostimgDto.DetailResDto> list) {
        List<PostimgDto.DetailResDto> newList = new ArrayList<>();
        for (PostimgDto.DetailResDto postimg : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(postimg.getId()).build()));
        }
        return newList;
    }

    @Override
    public List<PostimgDto.DetailResDto> list(PostimgDto.ListReqDto param) {
        List<PostimgDto.DetailResDto> list = new ArrayList<>();
        List<PostimgDto.DetailResDto> postimgs = postimgMapper.list(param);
        return addlist(postimgs);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(PostimgDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(postimgMapper.listCount(param));
        res.setList(addlist(postimgMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<PostimgDto.DetailResDto> scrollList(PostimgDto.ScrollListReqDto param) {
        return addlist(postimgMapper.scrollList(param));
    }
}
