package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;

public class CitySearchDTO implements Serializable {

    private String name;

    public CitySearchDTO(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
