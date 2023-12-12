package com.logiclytics.usermanagementservice.controller;

import com.logiclytics.usermanagementservice.model.Permission;
import com.logiclytics.usermanagementservice.service.impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionServiceImpl permissionServiceImpl;

    @Autowired
    public PermissionController(PermissionServiceImpl permissionServiceImpl) {
        this.permissionServiceImpl = permissionServiceImpl;
    }

    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionServiceImpl.getAllPermissions();
    }

    @GetMapping("/{id}")
    public Permission getPermissionById(@PathVariable Long id) {
        return permissionServiceImpl.getPermissionById(id);
    }

    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionServiceImpl.createPermission(permission);
    }

    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable Long id, @RequestBody Permission updatedPermission) {
        return permissionServiceImpl.updatePermission(id, updatedPermission);
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable Long id) {
        permissionServiceImpl.deletePermission(id);
    }
}
