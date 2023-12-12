package com.logiclytics.usermanagementservice.repository;

import com.logiclytics.usermanagementservice.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByRoleRoleName(String roleName);

    List<UserRole> findByUserUserId(Long userId);

    List<UserRole> findByUserUsername(String userName);

    boolean existsByUserUserIdAndRoleRoleName(Long userId, String roleName);
}

