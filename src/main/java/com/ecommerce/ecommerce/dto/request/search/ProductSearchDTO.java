package com.ecommerce.ecommerce.dto.request.search;

import java.io.Serializable;

public class ProductSearchDTO implements Serializable {
    private String name;
    private String detail;
    private Integer status;

    public ProductSearchDTO(String name, String detail, Integer status){
        this.name = name;
        this.detail = detail;
        this.status = status;
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

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }
}
