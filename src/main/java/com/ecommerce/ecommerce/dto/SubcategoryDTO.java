package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubcategoryDTO {

    private Long id;

    @NotBlank(message = "El campo nombre no debe estar vacío")
    private String name;

    @NotNull(message = "El campo categoría no debe estar vacío")
    private Long categoryId;

    private CategoryDTO category;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }

    public CategoryDTO getCategory(){
        return category;
    }

    public void setCategory(CategoryDTO category){
        this.category = category;
    }
}
