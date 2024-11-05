package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;

public class ProductSearchDTO implements Serializable {
    private String name;
    private String detail;

    public ProductSearchDTO(String name, String detail){
        this.name = name;
        this.detail = detail;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }
}
