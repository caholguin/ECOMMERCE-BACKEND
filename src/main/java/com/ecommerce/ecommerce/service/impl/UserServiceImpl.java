package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveUserDTO;
import com.ecommerce.ecommerce.entity.Role;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.exception.InvalidPasswordException;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.repository.UserRepository;
import com.ecommerce.ecommerce.service.RoleService;
import com.ecommerce.ecommerce.service.UserService;
import com.ecommerce.ecommerce.util.RoleEnum;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User registerCustomer(SaveUserDTO saveUserDTO){

        validatePassword(saveUserDTO);
        User user = new User();
        user.setName(saveUserDTO.getName());
        user.setUsername(saveUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(saveUserDTO.getPassword()));

        Role defaultRole = roleService.findDefaultRole().orElseThrow(()-> new ObjectNotFoundException("Role not found. Default role"));

        user.setRole(defaultRole);

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
