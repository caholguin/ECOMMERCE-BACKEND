package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.request.SaveVariantDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.service.VariantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("variants")
@CrossOrigin("*")
public class VariantController {

    @Autowired
    private VariantService variantService;

    @GetMapping
    public ResponseEntity<List<VariantDTO>>  getAllVariants() {
        List<VariantDTO> variants = variantService.findAll();
        return new ResponseEntity<>(variants, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VariantDTO> update(@PathVariable Long id, @RequestBody @Valid SaveVariantDTO saveVariantDTO){
        VariantDTO variant = variantService.updateStock(id, saveVariantDTO);
        return new ResponseEntity<>(variant, HttpStatus.OK);
    }

}
