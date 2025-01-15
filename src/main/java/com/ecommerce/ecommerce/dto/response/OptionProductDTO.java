package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.entity.Option;
import com.ecommerce.ecommerce.entity.Product;

import java.io.Serializable;

public class OptionProductDTO implements Serializable {
    private Long id;
    private String features;
    private ProductDTO product;
    private OptionDTO option;

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

    public ProductDTO getProduct(){
        return product;
    }

    public void setProduct(ProductDTO product){
        this.product = product;
    }

    public OptionDTO getOption(){
        return option;
    }

    public void setOption(OptionDTO option){
        this.option = option;
    }


    public static class OptionDTO implements Serializable {
        private Long id;
        private String name;

        public OptionDTO(Long id, String name){
            this.id = id;
            this.name = name;
        }

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

    }
}
