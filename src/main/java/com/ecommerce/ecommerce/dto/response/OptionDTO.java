package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class OptionDTO implements Serializable {

    private Long id;

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
