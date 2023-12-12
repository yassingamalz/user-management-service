package com.logiclytics.usermanagementservice.repository;

import com.logiclytics.usermanagementservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRoleName(String roleName);

    Role findByRoleName(String roleName);
}
