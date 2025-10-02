package com.ecommerce.ecommerce.dto.request.search;

import java.io.Serializable;

public class OptionSearchDTO implements Serializable {

    private String name;

    public OptionSearchDTO(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
