package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.RoleDTO;
import com.ecommerce.ecommerce.dto.RoleSummaryDTO;
import com.ecommerce.ecommerce.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();

    Page<RoleDTO> findAll(Pageable pageable);

    RoleDTO findById(Long id);

    RoleDTO save(RoleDTO roleDto);

    RoleSummaryDTO update(Long id, RoleDTO roleDto);

    void delete(Long id);
}
