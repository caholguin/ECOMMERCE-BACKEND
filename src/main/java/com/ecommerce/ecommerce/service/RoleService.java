package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();
}
