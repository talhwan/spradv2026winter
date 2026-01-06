package com.thc.sprbasic2025fall.service;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {
    DefaultDto.CreateResDto create(PermissionDto.CreateReqDto param, Long reqUserId);
    void update(PermissionDto.UpdateReqDto param, Long reqUserId);
    void delete(PermissionDto.UpdateReqDto param, Long reqUserId);
    PermissionDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId);
    List<PermissionDto.DetailResDto> list(PermissionDto.ListReqDto param, Long reqUserId);
    DefaultDto.PagedListResDto pagedList(PermissionDto.PagedListReqDto param, Long reqUserId);
    List<PermissionDto.DetailResDto> scrollList(PermissionDto.ScrollListReqDto param, Long reqUserId);
}
