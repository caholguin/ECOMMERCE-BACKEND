package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveUserDTO;
import com.ecommerce.ecommerce.dto.response.RegisteredUserDTO;
import jakarta.validation.Valid;

public interface AuthenticationService {

    RegisteredUserDTO registerCustomer(SaveUserDTO saveUserDTO);

}
