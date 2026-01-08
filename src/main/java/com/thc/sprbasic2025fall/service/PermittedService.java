package com.thc.sprbasic2025fall.service;

import com.thc.sprbasic2025fall.dto.PermissionDto;
import org.springframework.stereotype.Service;

@Service
public interface PermittedService {
    void check(String target, Integer func, Long userId);
    boolean ispermitted(String target, Integer func, Long userId);
}
