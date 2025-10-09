package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.OperationDTO;
import com.ecommerce.ecommerce.entity.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {

    public static OperationDTO toDTO(Operation operation) {
        OperationDTO operationDTO = new OperationDTO();

        operationDTO.setId(operation.getId());
        operationDTO.setName(operation.getName());
        operationDTO.setPath(operation.getPath());
        operationDTO.setHttpMethod(operation.getHttpMethod());
        operationDTO.setPermitAll(operation.isPermitAll());

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


}
