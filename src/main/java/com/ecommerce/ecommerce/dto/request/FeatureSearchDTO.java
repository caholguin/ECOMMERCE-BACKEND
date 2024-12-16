package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;

public class FeatureSearchDTO implements Serializable {

    private String name;

    private Long option;

    public FeatureSearchDTO(String name, Long option){
        this.name = name;
        this.option = option;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getOption(){
        return option;
    }

    public void setOption(Long option){
        this.option = option;
    }
}
