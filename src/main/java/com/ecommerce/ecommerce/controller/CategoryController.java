package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.CategorySearchDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
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

    @GetMapping()
    public ResponseEntity<Page<CategoryDTO>> findAll(Pageable pageable, @RequestParam(required = false) String name){

        CategorySearchDTO categorySearchDTO = new CategorySearchDTO(name);

        Page<CategoryDTO> categories = categoryService.findAll(categorySearchDTO,pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

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


    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody @Valid SaveCategoryDTO saveCategoryDTO){
        CategoryDTO category = categoryService.update(id, saveCategoryDTO);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
