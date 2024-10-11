package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class SaveFamilyDTO implements Serializable {

    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

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
}
