package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.FamilyDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.service.FamilyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTO> getById(@PathVariable long id) {

        FamilyDTO family = familyService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("No existe una familia con id: " + id));

        return new ResponseEntity<>(family,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<FamilyDTO> create(@RequestBody @Valid FamilyDTO familyDTO) {
        FamilyDTO family = familyService.save(familyDTO);
        return new ResponseEntity<>(family, HttpStatus.CREATED);
    }



}
