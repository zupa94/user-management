package com.mzupancic.usermanagement.repository;

import com.mzupancic.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void deleteByUsername(String username);
    User findOneById(Long id);
}
