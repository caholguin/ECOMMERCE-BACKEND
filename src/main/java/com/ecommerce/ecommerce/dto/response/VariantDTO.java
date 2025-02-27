package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;
import java.util.List;

public class VariantDTO {

    private Long id;

    private String image;

    private int stock;

    private ProductDTO product;

    private List<FeatureVariantDTO> featureVariants;

    private List<ImageVariantDTO> imageVariants;

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

    public List<ImageVariantDTO> getImageVariants(){
        return imageVariants;
    }

    public void setImagesVariant(List<ImageVariantDTO> imageVariants){
        this.imageVariants = imageVariants;
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

    public static class FeatureVariantDTO implements Serializable {
        private Long id;
        private FeatureDTO feature;

        public FeatureVariantDTO(Long id, FeatureDTO feature){
            this.id = id;
            this.feature = feature;
        }

        public Long getId(){
            return id;
        }

        public void setId(Long id){
            this.id = id;
        }

        public FeatureDTO getFeature(){
            return feature;
        }

        public void setFeature(FeatureDTO feature){
            this.feature = feature;
        }
    }
}
