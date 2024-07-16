package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class FamilyDTO {

    private Long id;

    @NotBlank(message = "El campo nombre no debe estar vacío")
    private String name;

    List<CategoryDTO> categories;

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

    public List<CategoryDTO> getCategories(){
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories){
        this.categories = categories;
    }
}
