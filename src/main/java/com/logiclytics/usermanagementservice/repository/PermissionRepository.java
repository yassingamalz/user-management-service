package com.logiclytics.usermanagementservice.repository;

import com.logiclytics.usermanagementservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByPermissionName(String permissionName);
}
