package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.SaveSubcategoryDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.dto.request.SubcategorySearchDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.service.SubCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping()
    public ResponseEntity<Page<SubcategoryDTO>> findAll(Pageable pageable, @RequestParam(required = false) String name){

        SubcategorySearchDTO subcategorySearchDTO = new SubcategorySearchDTO(name);

        Page<SubcategoryDTO> subCategories = subCategoryService.findAll(subcategorySearchDTO,pageable);
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SubcategoryDTO> create(@RequestBody @Valid SaveSubcategoryDTO saveSubcategoryDTO){
        SubcategoryDTO subCategory = subCategoryService.save(saveSubcategoryDTO);

        return new ResponseEntity<>(subCategory,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryDTO> findById(@PathVariable Long id){
        SubcategoryDTO subcategory = subCategoryService.findById(id);

        return new ResponseEntity<>(subcategory,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryDTO> update(@PathVariable Long id, @RequestBody @Valid SaveSubcategoryDTO saveSubcategoryDTO){
        SubcategoryDTO subcategory = subCategoryService.update(id,saveSubcategoryDTO);
        return new ResponseEntity<>(subcategory,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        subCategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
