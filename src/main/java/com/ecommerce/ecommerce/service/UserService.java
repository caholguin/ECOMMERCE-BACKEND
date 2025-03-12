package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveUserDTO;
import com.ecommerce.ecommerce.entity.User;

public interface UserService {
    User registerCustomer(SaveUserDTO saveUserDTO);
}
