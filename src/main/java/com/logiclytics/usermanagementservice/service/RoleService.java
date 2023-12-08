package com.logiclytics.usermanagementservice.service;

import com.logiclytics.usermanagementservice.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long roleId);

    Role createRole(Role role);

    Role updateRole(Long roleId, Role updatedRole);

    void deleteRole(Long roleId);
}
