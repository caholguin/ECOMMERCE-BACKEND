package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.OperationDTO;
import com.ecommerce.ecommerce.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService){
        this.operationService = operationService;
    }

    @GetMapping
    public ResponseEntity<List<OperationDTO>> findAll(){
        List<OperationDTO> operations = operationService.findAll();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

}
