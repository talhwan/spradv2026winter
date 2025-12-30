package com.thc.sprbasic2025fall.repository;

import com.thc.sprbasic2025fall.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    RefreshToken findByContent(String content);
    List<RefreshToken> findByUserId(Long userId);
}
