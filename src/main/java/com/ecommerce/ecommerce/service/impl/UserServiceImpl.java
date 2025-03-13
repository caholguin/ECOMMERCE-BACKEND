package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveUserDTO;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.exception.InvalidPasswordException;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.UserService;
import com.ecommerce.ecommerce.util.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerCustomer(SaveUserDTO saveUserDTO){

        validatePassword(saveUserDTO);

        User user = new User();
        user.setName(saveUserDTO.getUsername());
        user.setUsername(saveUserDTO.getUsername());
        user.setEmail(saveUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(saveUserDTO.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    private void validatePassword(SaveUserDTO saveUserDTO) {
        if (!StringUtils.hasText(saveUserDTO.getPassword()) || !StringUtils.hasText(saveUserDTO.getRepeatedPassword())){
            throw new InvalidPasswordException("Las contraseñas no coinciden");
        }

        if (!saveUserDTO.getPassword().equals(saveUserDTO.getRepeatedPassword())){
            throw new InvalidPasswordException("Las contraseñas no coinciden");
        }
    }
}
