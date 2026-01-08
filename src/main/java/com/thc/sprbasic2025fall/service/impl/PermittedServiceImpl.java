package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.PermissionDto;
import com.thc.sprbasic2025fall.dto.PermissiondetailDto;
import com.thc.sprbasic2025fall.exception.NoPermissionException;
import com.thc.sprbasic2025fall.mapper.PermissionMapper;
import com.thc.sprbasic2025fall.service.PermittedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PermittedServiceImpl implements PermittedService {

    final PermissionMapper permissionMapper;

    @Override
    public void check(String target, Integer func, Long userId) {
        boolean isable = ispermitted(target, func, userId);
        if(!isable){
            throw new NoPermissionException("no permission");
        }
    }

    @Override
    public boolean ispermitted(String target, Integer func, Long userId){
        if(userId != null && userId == (long) -200){
            //무조건 승인해달라는 뜻!
            return true;
        }
        int listCount = permissionMapper.ispermitted(PermissionDto.IspermittedReqDto.builder().target(target).func(func).userId(userId).build());
        return listCount > 0;
    }

}
