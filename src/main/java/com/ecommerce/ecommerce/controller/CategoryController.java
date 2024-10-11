package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid SaveCategoryDTO saveCategoryDTO){
        CategoryDTO category = categoryService.save(saveCategoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        CategoryDTO family = categoryService.findById(id);
        return new ResponseEntity<>(family, HttpStatus.OK);
    }

    /*

    @GetMapping()
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable){
        Page<CategoryDTO> categories = categoryService.findAll(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO){
        CategoryDTO category = categoryService.update(id, categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id){
        CategoryDTO category = categoryService.delete(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }*/
}
