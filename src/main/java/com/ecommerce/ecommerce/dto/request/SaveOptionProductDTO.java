package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;

public class SaveOptionProductDTO implements Serializable {
    private Long id;
    private String features;
    private Long productId;
    private Long optionId;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFeatures(){
        return features;
    }

    public void setFeatures(String features){
        this.features = features;
    }

    public Long getProductId(){
        return productId;
    }

    public void setProductId(Long productId){
        this.productId = productId;
    }

    public Long getOptionId(){
        return optionId;
    }

    public void setOptionId(Long optionId){
        this.optionId = optionId;
    }
}
