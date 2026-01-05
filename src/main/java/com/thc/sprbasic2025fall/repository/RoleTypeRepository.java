package com.thc.sprbasic2025fall.repository;

import com.thc.sprbasic2025fall.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleTypeRepository extends JpaRepository<RoleType, String>{
	RoleType findByTypeName(String typeName);
}
