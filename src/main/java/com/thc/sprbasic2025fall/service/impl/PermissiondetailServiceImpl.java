package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.Permissiondetail;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissiondetailDto;
import com.thc.sprbasic2025fall.mapper.PermissiondetailMapper;
import com.thc.sprbasic2025fall.repository.PermissiondetailRepository;
import com.thc.sprbasic2025fall.service.PermissiondetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PermissiondetailServiceImpl implements PermissiondetailService {

    final PermissiondetailRepository permissiondetailRepository;
    final PermissiondetailMapper permissiondetailMapper;

    @Override
    public void toggle(PermissiondetailDto.ToggleReqDto param, Long reqUserId) {
        /*if(param.getFlag()){
            //create
            Permissiondetail permissiondetail = permissiondetailRepository.findByPermissionIdAndTargetAndFunc(param.getPermissionId(), param.getTarget(), param.getFunc());
            if (permissiondetail == null) {
                create(PermissiondetailDto.CreateReqDto.builder().permissionId(param.getPermissionId()).target(param.getTarget()).func(param.getFunc()).build(), reqUserId);
            } else {
                permissiondetail.setDeleted(false);
                permissiondetailRepository.save(permissiondetail);
            }
        } else {
            //delete
            Permissiondetail permissiondetail = permissiondetailRepository.findByPermissionIdAndTargetAndFunc(param.getPermissionId(), param.getTarget(), param.getFunc());
            if (permissiondetail != null) {
                delete(PermissiondetailDto.UpdateReqDto.builder().id(permissiondetail.getId()).build(), reqUserId);
            }
        }*/
        Permissiondetail permissiondetail = permissiondetailRepository.findByPermissionIdAndTargetAndFunc(param.getPermissionId(), param.getTarget(), param.getFunc());
        if (permissiondetail == null) {
            create(PermissiondetailDto.CreateReqDto.builder().permissionId(param.getPermissionId()).target(param.getTarget()).func(param.getFunc()).build(), reqUserId);
        } else {
            permissiondetail.setDeleted(!param.getFlag());
            permissiondetailRepository.save(permissiondetail);
        }
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(PermissiondetailDto.CreateReqDto param, Long reqUserId) {
        Permissiondetail permissiondetail = permissiondetailRepository.findByPermissionIdAndTargetAndFunc(param.getPermissionId(), param.getTarget(), param.getFunc());
        if (permissiondetail != null) {
            return permissiondetail.toCreateResDto();
        }
        DefaultDto.CreateResDto res = permissiondetailRepository.save(param.toEntity()).toCreateResDto();
        return res;
    }

    @Override
    public void update(PermissiondetailDto.UpdateReqDto param, Long reqUserId) {
        Permissiondetail permissiondetail = permissiondetailRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        permissiondetail.update(param);
        permissiondetailRepository.save(permissiondetail);

    }

    @Override
    public void delete(PermissiondetailDto.UpdateReqDto param, Long reqUserId) {
        update(PermissiondetailDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    public PermissiondetailDto.DetailResDto get(DefaultDto.DetailReqDto param) {
        PermissiondetailDto.DetailResDto res = permissiondetailMapper.detail(param.getId());
        return res;
    }

    @Override
    public PermissiondetailDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param);
    }

    public List<PermissiondetailDto.DetailResDto> addlist(List<PermissiondetailDto.DetailResDto> list, Long reqUserId) {
        List<PermissiondetailDto.DetailResDto> newList = new ArrayList<>();
        for (PermissiondetailDto.DetailResDto permissiondetail : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(permissiondetail.getId()).build()));
        }
        return newList;
    }

    @Override
    public List<PermissiondetailDto.DetailResDto> list(PermissiondetailDto.ListReqDto param, Long reqUserId) {
        List<PermissiondetailDto.DetailResDto> list = new ArrayList<>();
        List<PermissiondetailDto.DetailResDto> permissiondetails = permissiondetailMapper.list(param);
        return addlist(permissiondetails, reqUserId);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(PermissiondetailDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(permissiondetailMapper.listCount(param));
        res.setList(addlist(permissiondetailMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<PermissiondetailDto.DetailResDto> scrollList(PermissiondetailDto.ScrollListReqDto param, Long reqUserId) {
        return addlist(permissiondetailMapper.scrollList(param), reqUserId);
    }
}
