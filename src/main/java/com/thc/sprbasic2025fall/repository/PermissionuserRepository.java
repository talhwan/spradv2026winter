package com.thc.sprbasic2025fall.repository;

import com.thc.sprbasic2025fall.domain.Permissionuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionuserRepository extends JpaRepository<Permissionuser, Long> {
    Permissionuser findByPermissionIdAndUserId(Long permissionId, Long userId);
}
