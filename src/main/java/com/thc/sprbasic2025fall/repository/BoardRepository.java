package com.thc.sprbasic2025fall.repository;

import com.thc.sprbasic2025fall.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
