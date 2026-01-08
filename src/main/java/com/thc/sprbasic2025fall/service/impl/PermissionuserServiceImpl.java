package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.Permissionuser;
import com.thc.sprbasic2025fall.domain.User;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissionuserDto;
import com.thc.sprbasic2025fall.exception.NoMatchingDataException;
import com.thc.sprbasic2025fall.mapper.PermissionuserMapper;
import com.thc.sprbasic2025fall.repository.PermissionuserRepository;
import com.thc.sprbasic2025fall.repository.UserRepository;
import com.thc.sprbasic2025fall.service.PermissionuserService;
import com.thc.sprbasic2025fall.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissionuserServiceImpl implements PermissionuserService {

    final PermissionuserRepository permissionuserRepository;
    final PermissionuserMapper permissionuserMapper;
    final UserRepository userRepository;
    final PermittedService permittedService;
    String target = "permission";

    @Override
    public DefaultDto.CreateResDto create(PermissionuserDto.CreateReqDto param, Long reqUserId) {
        permittedService.check(target, 110, reqUserId);

        if(param.getUserId() == null){
            User user = userRepository.findByUsername(param.getUsername());
            if(user == null){
                throw new NoMatchingDataException("no matching user");
            } else {
                param.setUserId(user.getId());
            }
        }

        Permissionuser permissionuser = permissionuserRepository.findByPermissionIdAndUserId(param.getPermissionId(), param.getUserId());
        if (permissionuser != null) {
            return permissionuser.toCreateResDto();
        }
        DefaultDto.CreateResDto res = permissionuserRepository.save(param.toEntity()).toCreateResDto();
        return res;
    }

    @Override
    public void update(PermissionuserDto.UpdateReqDto param, Long reqUserId) {
        permittedService.check(target, 120, reqUserId);

        Permissionuser permissionuser = permissionuserRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        permissionuser.update(param);
        permissionuserRepository.save(permissionuser);
    }

    @Override
    public void delete(PermissionuserDto.UpdateReqDto param, Long reqUserId) {
        update(PermissionuserDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    public PermissionuserDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        permittedService.check(target, 200, reqUserId);
        PermissionuserDto.DetailResDto res = permissionuserMapper.detail(param.getId());
        return res;
    }

    @Override
    public PermissionuserDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }

    public List<PermissionuserDto.DetailResDto> addlist(List<PermissionuserDto.DetailResDto> list, Long reqUserId) {
        List<PermissionuserDto.DetailResDto> newList = new ArrayList<>();
        for (PermissionuserDto.DetailResDto permissionuser : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(permissionuser.getId()).build(), reqUserId));
        }
        return newList;
    }

    @Override
    public List<PermissionuserDto.DetailResDto> list(PermissionuserDto.ListReqDto param, Long reqUserId) {
        List<PermissionuserDto.DetailResDto> list = new ArrayList<>();
        List<PermissionuserDto.DetailResDto> permissionusers = permissionuserMapper.list(param);
        return addlist(permissionusers, reqUserId);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(PermissionuserDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(permissionuserMapper.listCount(param));
        res.setList(addlist(permissionuserMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<PermissionuserDto.DetailResDto> scrollList(PermissionuserDto.ScrollListReqDto param, Long reqUserId) {
        return addlist(permissionuserMapper.scrollList(param), reqUserId);
    }
}
