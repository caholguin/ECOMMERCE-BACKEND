package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.entity.FeatureVariant;


import java.io.Serializable;
import java.util.List;

public class VariantDTO {

    private Long id;

    private String image;

    private ProductDTO product;

    private List<FeatureVariantDTO> featureVariants;

    private List<ImageVariantDTO> imagesVariant;

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

    public ProductDTO getProduct(){
        return product;
    }

    public void setProduct(ProductDTO product){
        this.product = product;
    }

    public List<FeatureVariantDTO> getFeatureVariants(){
        return featureVariants;
    }

    public void setFeatureVariants(List<FeatureVariantDTO> featureVariants){
        this.featureVariants = featureVariants;
    }

    public List<ImageVariantDTO> getImagesVariant(){
        return imagesVariant;
    }

    public void setImagesVariant(List<ImageVariantDTO> imagesVariant){
        this.imagesVariant = imagesVariant;
    }

    public static class ImageVariantDTO implements Serializable {
        private Long id;
        private String url;
        private Integer orderItems;

        public ImageVariantDTO(Long id, String url, Integer orderItems){
            this.id = id;
            this.url = url;
            this.orderItems = orderItems;
        }

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
    }
}
