package com.logiclytics.usermanagementservice.service;

import com.logiclytics.usermanagementservice.exception.DuplicateUserException;
import com.logiclytics.usermanagementservice.exception.UserNotFoundException;
import com.logiclytics.usermanagementservice.model.User;
import com.logiclytics.usermanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new UserNotFoundException("User not found with id: " + userId));
    }

    @Override
    public User createUser(User user) {
        validateUsernameAndEmail(user.getUsername(), user.getEmail());
        return userRepository.save(user);
    }

    private void validateUsernameAndEmail(String username, String email) {
        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            throw new DuplicateUserException("Username or email is already in use.");
        }
    }

    //TODO implement the update logic with mappers
    @Override
    public User updateUser(Long userId, User updatedUser) {
        if (userRepository.existsById(userId)) {
            updatedUser.setUserId(userId);
            return userRepository.save(updatedUser);
        } else {
            return null;
            //TODO Decide if you want to create a user that is not exist
        }
    }

    @Override
    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId))
            userRepository.deleteById(userId);
            //TODO implement a mechanism to delete a user that have already other entry in other tables
        else
            throw new UserNotFoundException("User not found with id: " + userId);
    }
}
