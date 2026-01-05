package com.thc.sprbasic2025fall.repository;

import com.thc.sprbasic2025fall.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username); // 0 1
    User findByNick(String nick); // 0 1
    User findByUsernameAndPassword(String email, String password); // 0 1

    @EntityGraph(attributePaths = {"userRoleType.roleType"})
    Optional<User> findEntityGraphRoleTypeById(Long id);
}
