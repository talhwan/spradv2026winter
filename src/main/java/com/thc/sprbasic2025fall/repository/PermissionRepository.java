package com.thc.sprbasic2025fall.repository;

import com.thc.sprbasic2025fall.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
