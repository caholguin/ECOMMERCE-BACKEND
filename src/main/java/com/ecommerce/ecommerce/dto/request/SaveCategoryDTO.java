package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


public class SaveCategoryDTO implements Serializable {

    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

    @NotBlank(message = "El campo icono es obligatorio")
    private String icon;

    @NotNull(message = "El campo familia es obligatorio")
    private Long familyId;

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

    public String getIcon(){
        return icon;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public Long getFamilyId(){
        return familyId;
    }

    public void setFamilyId(Long familyId){
        this.familyId = familyId;
    }
}
