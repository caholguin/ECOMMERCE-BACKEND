package com.ecommerce.ecommerce.dto.request;

public class SubcategorySearchDTO {

    private String name;

    public SubcategorySearchDTO(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
