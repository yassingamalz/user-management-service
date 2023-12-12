package com.logiclytics.usermanagementservice.service;

import com.logiclytics.usermanagementservice.model.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getAllPermissions();

    Permission getPermissionById(Long permissionId);

    Permission createPermission(Permission permission);

    Permission updatePermission(Long permissionId, Permission updatedPermission);

    void deletePermission(Long permissionId);
}
