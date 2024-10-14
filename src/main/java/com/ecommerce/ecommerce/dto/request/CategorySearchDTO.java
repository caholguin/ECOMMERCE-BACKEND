package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;

public class CategorySearchDTO implements Serializable {

    private String name;

    public CategorySearchDTO(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
