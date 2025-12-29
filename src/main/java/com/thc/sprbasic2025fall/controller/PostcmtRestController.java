package com.thc.sprbasic2025fall.controller;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PostcmtDto;
import com.thc.sprbasic2025fall.service.PostcmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/postcmt")
@RestController
public class PostcmtRestController {

    final PostcmtService postcmtService;

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody PostcmtDto.CreateReqDto param) {
        return ResponseEntity.ok(postcmtService.create(param));
    }
    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody PostcmtDto.UpdateReqDto param) {
        postcmtService.update(param);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody PostcmtDto.UpdateReqDto param) {
        postcmtService.delete(param);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<PostcmtDto.DetailResDto> detail(DefaultDto.DetailReqDto param) {
        return ResponseEntity.ok(postcmtService.detail(param));
    }
    @GetMapping("/list")
    public ResponseEntity<List<PostcmtDto.DetailResDto>> list(PostcmtDto.ListReqDto param) {
        return ResponseEntity.ok(postcmtService.list(param));
    }
    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(PostcmtDto.PagedListReqDto param) {
        return ResponseEntity.ok(postcmtService.pagedList(param));
    }
    @GetMapping("/scrollList")
    public ResponseEntity<List<PostcmtDto.DetailResDto>> scrollList(PostcmtDto.ScrollListReqDto param) {
        return ResponseEntity.ok(postcmtService.scrollList(param));
    }

}
