package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.SubcategoryDTO;
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
    public ResponseEntity<Page<SubcategoryDTO>> findAll(Pageable pageable){
        Page<SubcategoryDTO> subCategories = subCategoryService.findAll(pageable);
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SubcategoryDTO> create(@RequestBody @Valid SubcategoryDTO subcategoryDTO){
        SubcategoryDTO subCategory = subCategoryService.save(subcategoryDTO);

        return new ResponseEntity<>(subCategory,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryDTO> findById(@PathVariable Long id){
        SubcategoryDTO subcategory = subCategoryService.findById(id).orElseThrow(() -> new ObjectNotFoundException("No existe una subcategoria con el id " + id));

        return new ResponseEntity<>(subcategory,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryDTO> update(@PathVariable Long id, @RequestBody @Valid SubcategoryDTO subcategoryDTO){
        SubcategoryDTO subcategory = subCategoryService.update(id,subcategoryDTO);
        return new ResponseEntity<>(subcategory,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubcategoryDTO> delete(@PathVariable Long id){
        SubcategoryDTO subcategory = subCategoryService.delete(id);
        return new ResponseEntity<>(subcategory,HttpStatus.OK);
    }

}
