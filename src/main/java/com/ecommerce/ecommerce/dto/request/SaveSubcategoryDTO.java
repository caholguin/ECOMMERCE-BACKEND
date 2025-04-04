package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class SaveSubcategoryDTO implements Serializable {

    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

    @NotNull(message = "El campo categoría es obligatorio")
    private Long categoryId;

    @NotNull(message = "El campo icono es obligatorio")
    private String icon;

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

    public String getIcon(){
        return icon;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }
}
