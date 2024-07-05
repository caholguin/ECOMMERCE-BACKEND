package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.FamilyDTO;
import com.ecommerce.ecommerce.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/families")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @GetMapping
    public ResponseEntity<Page<FamilyDTO>> findAll(Pageable pageable) {
        Page<FamilyDTO> families = familyService.findAll(pageable);
        return new ResponseEntity<>(families, HttpStatus.OK);
    }
}
