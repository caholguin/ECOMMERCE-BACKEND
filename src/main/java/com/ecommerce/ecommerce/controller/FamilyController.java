package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.FamilySearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveFamilyDTO;
import com.ecommerce.ecommerce.dto.response.FamilyDTO;

import com.ecommerce.ecommerce.service.FamilyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/families")
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @GetMapping
    public ResponseEntity<Page<FamilyDTO>> findAll(Pageable pageable, @RequestParam(required = false) String name) {

        FamilySearchDTO familySearchDTO = new FamilySearchDTO(name);

        Page<FamilyDTO> families = familyService.findAll(familySearchDTO,pageable);
        return new ResponseEntity<>(families, HttpStatus.OK);
    }

  @PostMapping()
    public ResponseEntity<FamilyDTO> create(@RequestBody @Valid SaveFamilyDTO saveFamilyDTO) {
        FamilyDTO family = familyService.save(saveFamilyDTO);
        return new ResponseEntity<>(family, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FamilyDTO> getById(@PathVariable long id) {
        return new ResponseEntity<>(familyService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamilyDTO> update(@PathVariable long id, @RequestBody @Valid SaveFamilyDTO saveFamilyDTO) {
        FamilyDTO familyDto = familyService.update(id,saveFamilyDTO);
        return new ResponseEntity<>(familyDto,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        familyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
