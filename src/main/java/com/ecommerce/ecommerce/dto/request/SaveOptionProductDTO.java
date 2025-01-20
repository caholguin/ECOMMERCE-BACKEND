package com.ecommerce.ecommerce.dto.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SaveOptionProductDTO implements Serializable {
    private Long id;
    private List<Map<String, String>> features;
    private Long productId;
    private Long optionId;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public List<Map<String, String>> getFeatures(){
        return features;
    }

    public void setFeatures(List<Map<String, String>> features){
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
