package com.thc.sprbasic2025fall.repository;

import com.thc.sprbasic2025fall.domain.Permissiondetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissiondetailRepository extends JpaRepository<Permissiondetail, Long> {
    Permissiondetail findByPermissionIdAndTargetAndFunc(Long permissionId, String target, Integer func);
}
