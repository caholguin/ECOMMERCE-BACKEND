package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;

public class ImageVariantDTO implements Serializable {

    private Long id;

    private String url;

    private Integer orderItems;

    private VariantDTO variant;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public Integer getOrderItems(){
        return orderItems;
    }

    public void setOrderItems(Integer orderItems){
        this.orderItems = orderItems;
    }

    public VariantDTO getVariant(){
        return variant;
    }

    public void setVariant(VariantDTO variant){
        this.variant = variant;
    }

    public static class VariantDTO implements Serializable {
        private Long id;
        private String image;

        public VariantDTO(Long id, String image){
            this.id = id;
            this.image = image;
        }

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
    }
}
