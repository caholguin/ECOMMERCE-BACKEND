package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.OperationDTO;
import com.ecommerce.ecommerce.dto.response.ModuleDTO;
import com.ecommerce.ecommerce.entity.Operation;
import org.springframework.stereotype.Component;
import com.ecommerce.ecommerce.entity.Module;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperationMapper {

    public static OperationDTO toDto(Operation operation) {
        OperationDTO operationDTO = new OperationDTO();

        operationDTO.setId(operation.getId());
        operationDTO.setName(operation.getName());
        operationDTO.setPath(operation.getPath());
        operationDTO.setHttpMethod(operation.getHttpMethod());
        operationDTO.setPermitAll(operation.isPermitAll());

        if (operation.getModule() != null) {
            ModuleDTO moduleDTO = new ModuleDTO();
            moduleDTO.setId(operation.getModule().getId());
            moduleDTO.setName(operation.getModule().getName());
            operationDTO.setModule(moduleDTO);
        }

        return operationDTO;
    }

    public static Operation toEntity(OperationDTO operationDTO) {
        Operation operation = new Operation();

        operation.setId(operationDTO.getId());
        operation.setName(operationDTO.getName());
        operation.setPath(operationDTO.getPath());
        operationDTO.setHttpMethod(operation.getHttpMethod());
        operationDTO.setPermitAll(operation.isPermitAll());

        return operation;
    }


    public static List<OperationDTO> toDtoList(List<Operation> operations){
        if(operations == null) return null;

        return operations.stream().map(OperationMapper::toDto).collect(Collectors.toList());
    }


}
