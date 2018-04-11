package com.mzupancic.usermanagement.repository;

import com.mzupancic.usermanagement.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    boolean existsByName(String name);
    Privilege findOneByName(String name);
}
