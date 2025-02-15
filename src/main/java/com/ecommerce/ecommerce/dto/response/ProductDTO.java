package com.ecommerce.ecommerce.dto.response;

import com.ecommerce.ecommerce.entity.OptionProduct;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private String detail;
    private String image;
    private Double price;
    private int stock;
    private int status;
    private Double discount;
    private LocalDate startDateDiscount;
    private LocalDate endDateDiscount;
    private SubcategoryDTO subcategory;
    private List<VariantDTO> variants;
    private List<OptionProductDTO> optionsProduct;

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

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public Double getDiscount(){
        return discount;
    }

    public void setDiscount(Double discount){
        this.discount = discount;
    }

    public LocalDate getStartDateDiscount(){
        return startDateDiscount;
    }

    public void setStartDateDiscount(LocalDate startDateDiscount){
        this.startDateDiscount = startDateDiscount;
    }

    public LocalDate getEndDateDiscount(){
        return endDateDiscount;
    }

    public void setEndDateDiscount(LocalDate endDateDiscount){
        this.endDateDiscount = endDateDiscount;
    }

    public SubcategoryDTO getSubcategory(){
        return subcategory;
    }

    public void setSubcategory(SubcategoryDTO subcategory){
        this.subcategory = subcategory;
    }

    public List<VariantDTO> getVariants(){
        return variants;
    }

    public void setVariants(List<VariantDTO> variants){
        this.variants = variants;
    }

    public List<OptionProductDTO> getOptionsProduct(){
        return optionsProduct;
    }

    public void setOptionsProduct(List<OptionProductDTO> optionsProduct){
        this.optionsProduct = optionsProduct;
    }

    public static class SubcategoryDTO implements Serializable {
        private Long id;
        private String name;

        public SubcategoryDTO(Long id, String name){
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

    public static class VariantDTO implements Serializable {
        private Long id;
        private String image;
        private List<ImageVariantDTO> imageVariants;
        private List<FeatureVariantDTO> featureVariants;

        public VariantDTO(Long id, String image, List<ImageVariantDTO> imageVariants, List<FeatureVariantDTO> featureVariants){
            this.id = id;
            this.image = image;
            this.imageVariants = imageVariants;
            this.featureVariants = featureVariants;
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

        public List<ImageVariantDTO> getImageVariants(){
            return imageVariants;
        }

        public void setImageVariants(List<ImageVariantDTO> imageVariants){
            this.imageVariants = imageVariants;
        }

        public List<FeatureVariantDTO> getFeatureVariants(){
            return featureVariants;
        }

        public void setFeatureVariants(List<FeatureVariantDTO> featureVariants){
            this.featureVariants = featureVariants;
        }
    }

    public static class FeatureVariantDTO implements Serializable {
        private Long id;
        private FeatureDTO feature;

        public FeatureVariantDTO(Long id,  FeatureDTO feature){
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

    public static class OptionProductDTO implements Serializable {
        private Long id;
        private List<Map<String, String>> features;
        private OptionDTO option;


        public OptionProductDTO(Long id, List<Map<String, String>> features, OptionDTO option){
            this.id = id;
            this.features = features;
            this.option = option;
        }

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

        public OptionDTO getOption(){
            return option;
        }

        public void setOption(OptionDTO option){
            this.option = option;
        }
    }

}
