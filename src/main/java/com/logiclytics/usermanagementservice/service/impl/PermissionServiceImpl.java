package com.logiclytics.usermanagementservice.service.impl;

import com.logiclytics.usermanagementservice.exception.DuplicatePermissionException;
import com.logiclytics.usermanagementservice.exception.PermissionNotFoundException;
import com.logiclytics.usermanagementservice.model.Permission;
import com.logiclytics.usermanagementservice.repository.PermissionRepository;
import com.logiclytics.usermanagementservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionById(Long permissionId) {
        return permissionRepository.findById(permissionId).
                orElseThrow(() -> new PermissionNotFoundException("Permission not found with id: " + permissionId));

    }

    @Override
    public Permission createPermission(Permission permission) {
        validatePermission(permission.getPermissionName());
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long permissionId, Permission updatedPermission) {
        if (permissionRepository.existsById(permissionId)) {
            updatedPermission.setPermissionId(permissionId);
            //TODO implement verification
            return permissionRepository.save(updatedPermission);
        } else
            throw new PermissionNotFoundException("Permission not found with id: " + permissionId);
    }

    @Override
    public void deletePermission(Long permissionId) {
        if (permissionRepository.existsById(permissionId))
            permissionRepository.deleteById(permissionId);
        else
            throw new PermissionNotFoundException("Permission not found with id: " + permissionId);
    }

    private void validatePermission(String permissionName) {
        if (permissionRepository.existsByPermissionName(permissionName))
            throw new DuplicatePermissionException("Permission '" + permissionName + "' already exists.");
    }

}
