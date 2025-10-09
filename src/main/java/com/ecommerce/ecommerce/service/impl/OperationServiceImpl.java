package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.OperationDTO;
import com.ecommerce.ecommerce.entity.Operation;
import com.ecommerce.ecommerce.mapper.OperationMapper;
import com.ecommerce.ecommerce.repository.OperationRepository;
import com.ecommerce.ecommerce.service.OperationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;


    public OperationServiceImpl(OperationRepository operationRepository){
        this.operationRepository = operationRepository;
    }

    @Override
    public List<OperationDTO> getOperations(){
        List<Operation> roles = operationRepository.findAll();
        return roles.stream()
                .map(OperationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
