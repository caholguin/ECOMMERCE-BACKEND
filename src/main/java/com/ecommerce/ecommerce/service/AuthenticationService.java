package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.LoginRequestDTO;
import com.ecommerce.ecommerce.dto.request.SaveUserDTO;
import com.ecommerce.ecommerce.dto.response.LoginResponseDTO;
import com.ecommerce.ecommerce.dto.response.RegisteredUserDTO;
import com.ecommerce.ecommerce.dto.response.UserDTO;
import com.ecommerce.ecommerce.entity.User;
import jakarta.validation.Valid;

public interface AuthenticationService {

    RegisteredUserDTO registerCustomer(SaveUserDTO saveUserDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    boolean validateToken(String jwt);

    UserDTO findLoggedInUser();
}
