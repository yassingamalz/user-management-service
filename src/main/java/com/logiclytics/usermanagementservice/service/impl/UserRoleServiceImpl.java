package com.logiclytics.usermanagementservice.service.impl;

import com.logiclytics.usermanagementservice.exception.DuplicateUserRoleException;
import com.logiclytics.usermanagementservice.exception.RoleNotFoundException;
import com.logiclytics.usermanagementservice.exception.UserNotFoundException;
import com.logiclytics.usermanagementservice.exception.UserRoleNotFoundException;
import com.logiclytics.usermanagementservice.model.Role;
import com.logiclytics.usermanagementservice.model.User;
import com.logiclytics.usermanagementservice.model.UserRole;
import com.logiclytics.usermanagementservice.repository.RoleRepository;
import com.logiclytics.usermanagementservice.repository.UserRepository;
import com.logiclytics.usermanagementservice.repository.UserRoleRepository;
import com.logiclytics.usermanagementservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository,
                               UserRepository userRepository,
                               RoleRepository roleRepository
    ) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole getUserRoleById(Long userRoleId) {
        return userRoleRepository.findById(userRoleId).
                orElseThrow(() -> new UserRoleNotFoundException("UserRole not found with id: " + userRoleId));
    }

    @Override
    public UserRole createUserRole(UserRole userRole) {
        validateUser(userRole);
        validateRole(userRole);
        validateUserRole(userRole);
        return userRoleRepository.save(userRole);
    }

    //TODO understand if it necessary to have an update or not
    @Override
    public UserRole updateUserRole(Long userRoleId, UserRole updatedUserRole) {
        // You may add validation logic here before updating
        if (userRoleRepository.existsById(userRoleId)) {
            updatedUserRole.setUserRoleId(userRoleId);
            return userRoleRepository.save(updatedUserRole);
        } else {
            // Handle not found scenario
            return null;
        }
    }

    @Override
    public void deleteUserRole(Long userRoleId) {
        if (!(userRoleRepository.existsById(userRoleId)))
            throw new UserRoleNotFoundException("User role not found with id:" + userRoleId);
        userRoleRepository.deleteById(userRoleId);
    }

    @Override
    public List<UserRole> getUsersByRole(String role) {
        return userRoleRepository.findByRoleRoleName(role);
    }

    @Override
    public List<UserRole> getUserRolesByUserId(Long userId) {
        return userRoleRepository.findByUserUserId(userId);
    }

    @Override
    public List<UserRole> getUserRolesByUserName(String userName) {
        return userRoleRepository.findByUserUsername(userName);
    }

    private void validateRole(UserRole userRole) {
        Role role = userRole.getRole();
        if (Objects.nonNull(role)) {
            if (roleRepository.existsByRoleName(role.getRoleName()))
                userRole.setRole(roleRepository.findByRoleName(role.getRoleName()));
            else
                throw new RoleNotFoundException("Role not found with name: " + role.getRoleName());
        }
    }

    private void validateUser(UserRole userRole) {
        if (Objects.nonNull(userRole.getUser())) {
            if (Objects.nonNull(userRole.getUser().getUserId()))
                validateUserWithUserId(userRole);
            if (Objects.nonNull(userRole.getUser().getUsername()))
                validateUserWithUserName(userRole);
        }
    }

    private void validateUserWithUserId(UserRole userRole) {
        User user = userRole.getUser();
        if (userRepository.existsById(user.getUserId()))
            userRole.setUser(userRepository.findById(user.getUserId()).
                    orElseThrow(() -> new UserNotFoundException("User not found with id: " + user.getUserId())));
    }

    private void validateUserWithUserName(UserRole userRole) {
        User user = userRole.getUser();
        if (userRepository.existsByUsername(user.getUsername()))
            userRole.setUser(userRepository.findByUsername(user.getUsername()));
        else
            throw new UserNotFoundException("User not found with name: " + user.getUsername());
    }

    private void validateUserRole(UserRole userRole) {
        if (userRoleRepository.existsByUserUserIdAndRoleRoleName(userRole.getUser().getUserId()
                , userRole.getRole().getRoleName())) {
            throw new DuplicateUserRoleException("User with name: " + userRole.getUser().getUsername() +
                    " already has the role: " + userRole.getRole().getRoleName());
        }
    }
}
