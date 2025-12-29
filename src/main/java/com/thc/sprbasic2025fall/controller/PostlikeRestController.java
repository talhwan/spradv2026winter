package com.thc.sprbasic2025fall.controller;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostlikeDto;
import com.thc.sprbasic2025fall.service.PostlikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/postlike")
@RestController
public class PostlikeRestController {

    final PostlikeService postlikeService;

    @GetMapping("/liked")
    public ResponseEntity<PostlikeDto.LikedResDto> liked(PostlikeDto.LikedReqDto param) {
        return ResponseEntity.ok(postlikeService.liked(param));
    }
    @PostMapping("/toggle")
    public ResponseEntity<PostlikeDto.ToggleResDto> toggle(@RequestBody PostlikeDto.CreateReqDto param) {
        return ResponseEntity.ok(postlikeService.toggle(param));
    }

    /**/

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PostlikeDto.CreateReqDto param) {
        return ResponseEntity.ok(postlikeService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostlikeDto.UpdateReqDto param) {
        postlikeService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostlikeDto.UpdateReqDto param) {
        postlikeService.delete(param);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<PostlikeDto.DetailResDto> detail(DefaultDto.DetailReqDto param) {
        return ResponseEntity.ok(postlikeService.detail(param));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostlikeDto.DetailResDto>> list(PostlikeDto.ListReqDto param) {
        return ResponseEntity.ok(postlikeService.list(param));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PostlikeDto.PagedListReqDto param) {
        return ResponseEntity.ok(postlikeService.pagedList(param));
    }
    @GetMapping("/scrollList")
    public ResponseEntity<List<PostlikeDto.DetailResDto>> scrollList(PostlikeDto.ScrollListReqDto param) {
        return ResponseEntity.ok(postlikeService.scrollList(param));
    }

}
