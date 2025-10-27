package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveUserDTO;
import com.ecommerce.ecommerce.dto.response.UserDTO;
import com.ecommerce.ecommerce.entity.User;

import java.util.Optional;

public interface UserService {
    User registerCustomer(SaveUserDTO saveUserDTO);

    Optional<User> findByUsername(String username);

    User findByIdEntity(Long id);

    UserDTO updatePasswordForRecovery(Long id, String newPassword);
}
