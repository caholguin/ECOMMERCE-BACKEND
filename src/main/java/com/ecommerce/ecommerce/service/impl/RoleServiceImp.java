package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.entity.Role;
import com.ecommerce.ecommerce.repository.RoleRepository;
import com.ecommerce.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {

    @Value("${security.default.role}")
    private String defaultRole;

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findDefaultRole(){
        return roleRepository.findByName(defaultRole);
    }
}
