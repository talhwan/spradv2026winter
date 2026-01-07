package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.Permission;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissionDto;
import com.thc.sprbasic2025fall.dto.PermissiondetailDto;
import com.thc.sprbasic2025fall.mapper.PermissionMapper;
import com.thc.sprbasic2025fall.repository.PermissionRepository;
import com.thc.sprbasic2025fall.service.PermissionService;
import com.thc.sprbasic2025fall.service.PermissiondetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    final PermissionRepository permissionRepository;
    final PermissionMapper permissionMapper;
    final PermissiondetailService permissiondetailService;

    @Override
    public DefaultDto.CreateResDto create(PermissionDto.CreateReqDto param, Long reqUserId) {
        DefaultDto.CreateResDto res = permissionRepository.save(param.toEntity()).toCreateResDto();
        return res;
    }

    @Override
    public void update(PermissionDto.UpdateReqDto param, Long reqUserId) {
        Permission permission = permissionRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        permission.update(param);
        permissionRepository.save(permission);

    }

    @Override
    public void delete(PermissionDto.UpdateReqDto param, Long reqUserId) {
        update(PermissionDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    public PermissionDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        PermissionDto.DetailResDto res = permissionMapper.detail(param.getId());
        res.setDetails(permissiondetailService.list(PermissiondetailDto.ListReqDto.builder().deleted(false).permissionId(res.getId()).build(), reqUserId));
        res.setTargets(PermissionDto.targets);
        return res;
    }

    @Override
    public PermissionDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }

    public List<PermissionDto.DetailResDto> addlist(List<PermissionDto.DetailResDto> list, Long reqUserId) {
        List<PermissionDto.DetailResDto> newList = new ArrayList<>();
        for (PermissionDto.DetailResDto permission : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(permission.getId()).build(), reqUserId));
        }
        return newList;
    }

    @Override
    public List<PermissionDto.DetailResDto> list(PermissionDto.ListReqDto param, Long reqUserId) {
        List<PermissionDto.DetailResDto> list = new ArrayList<>();
        List<PermissionDto.DetailResDto> permissions = permissionMapper.list(param);
        return addlist(permissions, reqUserId);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(PermissionDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(permissionMapper.listCount(param));
        res.setList(addlist(permissionMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<PermissionDto.DetailResDto> scrollList(PermissionDto.ScrollListReqDto param, Long reqUserId) {
        return addlist(permissionMapper.scrollList(param), reqUserId);
    }
}
