package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.search.CategorySearchDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

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

    @GetMapping("/top-categories-by-sales")
    public ResponseEntity<List<CategoryDTO>> findTopCategoriesBySales(){
        List<CategoryDTO> categories = categoryService.findTopCategoriesBySales();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<CategoryDTO> findBySlug(@PathVariable String slug) {
        CategoryDTO category = categoryService.findBySlug(slug);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
}
