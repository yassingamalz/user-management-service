package com.logiclytics.usermanagementservice.controller;

import com.logiclytics.usermanagementservice.model.UserRole;
import com.logiclytics.usermanagementservice.service.impl.UserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/roles")
public class UserRoleController {

    private final UserRoleServiceImpl userRoleService;

    @Autowired
    public UserRoleController(UserRoleServiceImpl userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public List<UserRole> getAllUserRoles() {
        return userRoleService.getAllUserRoles();
    }

    @GetMapping("/{id}")
    public UserRole getUserRoleById(@PathVariable Long id) {
        return userRoleService.getUserRoleById(id);
    }

    @GetMapping("/byRole/{role}")
    public List<UserRole> getUsersByRole(@PathVariable String role) {
        return userRoleService.getUsersByRole(role);
    }

    @GetMapping("/userById/{userId}")
    public List<UserRole> getUserRolesByUserId(@PathVariable Long userId) {
        return userRoleService.getUserRolesByUserId(userId);
    }

    @GetMapping("/userByUserName/{userName}")
    public List<UserRole> getUserRolesByUserName(@PathVariable String  userName) {
        return userRoleService.getUserRolesByUserName(userName);
    }

    @PostMapping
    public UserRole createUserRole(@RequestBody UserRole userRole) {
        return userRoleService.createUserRole(userRole);
    }

    //TODO understand if it necessary to have an update or not
    @PutMapping("/{id}")
    public UserRole updateUserRole(@PathVariable Long id, @RequestBody UserRole updatedUserRole) {
        return userRoleService.updateUserRole(id, updatedUserRole);
    }

    @DeleteMapping("/{id}")
    public void deleteUserRole(@PathVariable Long id) {
        userRoleService.deleteUserRole(id);
    }

}
