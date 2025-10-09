package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.OperationDTO;
import com.ecommerce.ecommerce.dto.RoleDTO;
import com.ecommerce.ecommerce.dto.RoleSummaryDTO;
import com.ecommerce.ecommerce.entity.Permission;
import com.ecommerce.ecommerce.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    private final OperationMapper operationMapper;

    public RoleMapper(OperationMapper operationMapper){
        this.operationMapper = operationMapper;
    }

    public static RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());

        List<OperationDTO> operationDTOs;

        if (role.getPermissions() != null) {
            operationDTOs = role.getPermissions().stream()
                    .map(Permission::getOperation)
                    .map(OperationMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            operationDTOs = new ArrayList<>();
        }

        roleDTO.setOperations(operationDTOs);

        return roleDTO;
    }

    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();

        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());

        return role;
    }


    public static RoleSummaryDTO RoleSummaryDTO(Role role) {
        RoleSummaryDTO roleSummaryDTO = new RoleSummaryDTO();

        roleSummaryDTO.setId(role.getId());
        roleSummaryDTO.setName(role.getName());

        return roleSummaryDTO;
    }
}
