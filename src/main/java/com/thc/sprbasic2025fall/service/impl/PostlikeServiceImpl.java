package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.Posting;
import com.thc.sprbasic2025fall.domain.Postlike;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostlikeDto;
import com.thc.sprbasic2025fall.mapper.PostlikeMapper;
import com.thc.sprbasic2025fall.repository.PostingRepository;
import com.thc.sprbasic2025fall.repository.PostlikeRepository;
import com.thc.sprbasic2025fall.service.PostlikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostlikeServiceImpl implements PostlikeService {

    final PostlikeRepository postlikeRepository;
    final PostingRepository postingRepository;
    final PostlikeMapper postlikeMapper;

    @Override
    public PostlikeDto.LikedResDto liked(PostlikeDto.LikedReqDto param) {
        Postlike postlike = postlikeRepository.findByPostingIdAndUserId(param.getPostingId(),param.getUserId());
        boolean liked = false;
        if(postlike != null){
            Boolean deleted = postlike.getDeleted();
            liked = !deleted;
        }
        return PostlikeDto.LikedResDto.builder().liked(liked).build();
    }

    @Override
    public PostlikeDto.ToggleResDto toggle(PostlikeDto.CreateReqDto param) {
        Postlike postlike = postlikeRepository.findByPostingIdAndUserId(param.getPostingId(),param.getUserId());
        boolean liked = false;
        if(postlike == null){
            create(param);
            liked = true;
        } else {
            Boolean deleted = postlike.getDeleted();
            postlike.setDeleted(!deleted);
            liked = deleted;
            postlikeRepository.save(postlike);
        }

        int countlike = postlikeMapper.listCount(PostlikeDto.PagedListReqDto.builder().deleted(false).postingId(param.getPostingId()).build());
        Posting posting = postingRepository.findById(param.getPostingId()).orElseThrow(() -> new RuntimeException("no data"));
        posting.setCountlike(countlike);
        postingRepository.save(posting);

        return PostlikeDto.ToggleResDto.builder().liked(liked).countlike(countlike).build();
    }

    @Override
    public DefaultDto.CreateResDto create(PostlikeDto.CreateReqDto param) {
        return postlikeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(PostlikeDto.UpdateReqDto param) {
        Postlike postlike = postlikeRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        postlike.update(param);
        postlikeRepository.save(postlike);
    }

    @Override
    public void delete(PostlikeDto.UpdateReqDto param) {
        update(PostlikeDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public PostlikeDto.DetailResDto get(DefaultDto.DetailReqDto param) {
        PostlikeDto.DetailResDto res = postlikeMapper.detail(param.getId());
        return res;
    }

    @Override
    public PostlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param);
    }

    public List<PostlikeDto.DetailResDto> addlist(List<PostlikeDto.DetailResDto> list) {
        List<PostlikeDto.DetailResDto> newList = new ArrayList<>();
        for (PostlikeDto.DetailResDto postlike : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(postlike.getId()).build()));
        }
        return newList;
    }

    @Override
    public List<PostlikeDto.DetailResDto> list(PostlikeDto.ListReqDto param) {
        List<PostlikeDto.DetailResDto> list = new ArrayList<>();
        List<PostlikeDto.DetailResDto> postlikes = postlikeMapper.list(param);
        return addlist(postlikes);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(PostlikeDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(postlikeMapper.listCount(param));
        res.setList(addlist(postlikeMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<PostlikeDto.DetailResDto> scrollList(PostlikeDto.ScrollListReqDto param) {
        return addlist(postlikeMapper.scrollList(param));
    }
}
