package com.ecommerce.ecommerce.dto.request.search;

import java.io.Serializable;

public class OrderSearchDTO implements Serializable {

    private Long id;

    public OrderSearchDTO(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
