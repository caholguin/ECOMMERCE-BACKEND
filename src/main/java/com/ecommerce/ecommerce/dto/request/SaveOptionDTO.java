package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class SaveOptionDTO implements Serializable {

    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

    private int type;

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

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }
}
