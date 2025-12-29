package com.thc.sprbasic2025fall.repository;

import com.thc.sprbasic2025fall.domain.Postlike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostlikeRepository extends JpaRepository<Postlike, Long> {
    Postlike findByPostingIdAndUserId(Long postingId, Long userId);
}
