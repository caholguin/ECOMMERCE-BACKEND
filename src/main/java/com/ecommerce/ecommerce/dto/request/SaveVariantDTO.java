package com.ecommerce.ecommerce.dto.request;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class SaveVariantDTO implements Serializable {

    private Long id;

    private String image;

    @NotNull(message = "El campo stock es obligatorio")
    private int stock;

    private Long productId;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public Long getProductId(){
        return productId;
    }

    public void setProductId(Long productId){
        this.productId = productId;
    }
}
