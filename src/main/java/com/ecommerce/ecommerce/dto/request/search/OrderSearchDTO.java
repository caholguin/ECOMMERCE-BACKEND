package com.ecommerce.ecommerce.dto.request.search;

import java.io.Serializable;

public class OrderSearchDTO implements Serializable {

    private Long id;

    private Long status;

    public OrderSearchDTO(Long id, Long status){
        this.id = id;
        this.status = status;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getStatus(){
        return status;
    }

    public void setStatus(Long status){
        this.status = status;
    }
}
