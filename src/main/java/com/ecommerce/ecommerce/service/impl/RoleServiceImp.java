package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.RoleDTO;
import com.ecommerce.ecommerce.dto.RoleSummaryDTO;
import com.ecommerce.ecommerce.entity.Operation;
import com.ecommerce.ecommerce.entity.Permission;
import com.ecommerce.ecommerce.entity.Role;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.OperationMapper;
import com.ecommerce.ecommerce.mapper.RoleMapper;
import com.ecommerce.ecommerce.repository.PermissionRepository;
import com.ecommerce.ecommerce.repository.RoleRepository;
import com.ecommerce.ecommerce.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImp implements RoleService {

    @Value("${security.default.role}")
    private String defaultRole;

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImp(RoleRepository roleRepository, PermissionRepository permissionRepository, RoleMapper roleMapper){
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Optional<Role> findDefaultRole(){
        return roleRepository.findByName(defaultRole);
    }

    @Override
    public Page<RoleDTO> findAll(Pageable pageable){
        Page<Role> roles = roleRepository.findAll(pageable);
        return roles.map(RoleMapper::toDTO);
    }

    @Override
    public RoleDTO findById(Long id){
        return RoleMapper.toDTO(this.findByIdEntity(id));
    }

    @Override
    public RoleDTO save(RoleDTO roleDto){
        Role role = roleMapper.toEntity(roleDto);
        // Mapear las operaciones desde DTO a entidades
        List<Operation> operations = roleDto.getOperations().stream()
                .map(OperationMapper::toEntity)
                .toList();

        role = roleRepository.save(role);
        // Crear los objetos permission y asociarlos al rol
        for (Operation operation : operations) {
            Permission permission = new Permission();
            permission.setRole(role);
            permission.setOperation(operation);

            permissionRepository.save(permission);
        }
        return RoleMapper.toDTO(role);
    }

    @Override
    @Transactional
    public RoleSummaryDTO update(Long id, RoleDTO roleDto){

        Role role = this.findByIdEntity(id);
        role.setName(roleDto.getName());

        // Mapear las operaciones desde DTO a entidades
        List<Operation> operations = roleDto.getOperations().stream()
                .map(OperationMapper::toEntity)
                .toList();

        role = roleRepository.save(role);

        //borrar permisos
        permissionRepository.deleteByRole(role);

        // Crear los objetos permission y asociarlos al rol
        Role finalRole = role;
        List<Permission> permissions = operations.stream()
                .map(operation -> {
                    Permission p = new Permission();
                    p.setRole(finalRole);
                    p.setOperation(operation);
                    return p;
                })
                .toList();

        permissionRepository.saveAll(permissions);


        return RoleMapper.RoleSummaryDTO(role);
    }

    @Override
    public void delete(Long id){
        Role role = this.findByIdEntity(id);

        roleRepository.delete(role);
    }

    private Role findByIdEntity(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Rol con ID: " + id + " no encontrado"));

    }
}
