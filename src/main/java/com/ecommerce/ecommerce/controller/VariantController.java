package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("variants")
public class VariantController {

    @Autowired
    private VariantService variantService;

    @GetMapping
    public ResponseEntity<List<VariantDTO>>  getAllVariants() {
        List<VariantDTO> variants = variantService.findAll();
        return new ResponseEntity<>(variants, HttpStatus.OK);
    }

}
