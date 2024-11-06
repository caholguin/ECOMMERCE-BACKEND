package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.entity.Variant;

import java.io.Serializable;

public class FeatureVariantDTO implements Serializable {

    private Long id;
    private Variant variant;
    private Feature feature;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Variant getVariant(){
        return variant;
    }

    public void setVariant(Variant variant){
        this.variant = variant;
    }

    public Feature getFeature(){
        return feature;
    }

    public void setFeature(Feature feature){
        this.feature = feature;
    }
}
