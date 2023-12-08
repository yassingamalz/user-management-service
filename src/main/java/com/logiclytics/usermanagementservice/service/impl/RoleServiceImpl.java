package com.logiclytics.usermanagementservice.service.impl;

import com.logiclytics.usermanagementservice.exception.DuplicateRoleException;
import com.logiclytics.usermanagementservice.exception.RoleNotFoundException;
import com.logiclytics.usermanagementservice.model.Role;
import com.logiclytics.usermanagementservice.repository.RoleRepository;
import com.logiclytics.usermanagementservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).
                orElseThrow(() -> new RoleNotFoundException("Role not found with id: " + roleId));
    }

    @Override
    public Role createRole(Role role) {
        validateRole(role);
        return roleRepository.save(role);
    }

    private void validateRole(Role role) {
        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new DuplicateRoleException("Role with name '" + role.getRoleName() + "' already exists.");
        }
    }

    //TODO implement the update logic with mappers
    @Override
    public Role updateRole(Long roleId, Role updatedRole) {
        if (roleRepository.existsById(roleId)) {
            updatedRole.setRoleId(roleId);
            return roleRepository.save(updatedRole);
        } else {
            return null;
            //TODO Decide if you want to create a Role that is not exist
        }
    }

    @Override
    public void deleteRole(Long roleId) {
        if (roleRepository.existsById(roleId))
            roleRepository.deleteById(roleId);
            //TODO implement a mechanism to delete a role that have already other entry in other tables
        else
            throw new RoleNotFoundException("Role not found with id: " + roleId);
    }

}
