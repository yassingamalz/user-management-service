package com.logiclytics.usermanagementservice.service;

import com.logiclytics.usermanagementservice.model.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> getAllUserRoles();

    UserRole getUserRoleById(Long userRoleId);

    UserRole createUserRole(UserRole userRole);

    UserRole updateUserRole(Long userRoleId, UserRole updatedUserRole);

    void deleteUserRole(Long userRoleId);

    List<UserRole> getUsersByRole(String role);

    List<UserRole> getUserRolesByUserId(Long userId);

    List<UserRole> getUserRolesByUserName(String userName);
}