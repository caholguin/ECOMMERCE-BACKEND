package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;

public class FamilySearchDTO implements Serializable {

    private String name;

    public FamilySearchDTO(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
