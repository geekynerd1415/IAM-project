package com.iamlab.platform.service;

import com.iamlab.platform.dto.CreateUserRequest;
import com.iamlab.platform.dto.UserResponse;
import com.iamlab.platform.exception.UserNotFoundException;
import com.iamlab.platform.model.User;
import com.iamlab.platform.repository.UserRepository;

import com.iamlab.platform.exception.DuplicateEmailException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {

        User user = userRepository.save(
                request.name(),
                request.email());

        return toResponse(user);
    }

    public UserResponse getUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found: " + id));

        return toResponse(user);
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private UserResponse toResponse(User user) {

        return new UserResponse(
                user.id(),
                user.name(),
                user.email());
    }

    public UserResponse updateUser(
            Long id,
            CreateUserRequest request) {
        try {
            int rowsUpdated = userRepository.update(
                    id,
                    request.name(),
                    request.email());

            if (rowsUpdated == 0) {
                throw new UserNotFoundException(
                        "User not found: " + id);
            }

            return getUser(id);

        } catch (DuplicateKeyException exception) {
            throw new DuplicateEmailException(
                    "A user with this email already exists");
        }
    }

    public void deleteUser(Long id) {
        int rowsDeleted = userRepository.delete(id);

        if (rowsDeleted == 0) {
            throw new UserNotFoundException(
                    "User not found: " + id);
        }
    }
}