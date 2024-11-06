package com.ecommerce.ecommerce.dto.response;

import java.io.Serializable;
import java.util.List;

public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private String detail;
    private String image;
    private Double price;
    private int stock;
    private int status;
    private SubcategoryDTO subcategory;
    private List<VariantDTO> variants;

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

        public VariantDTO(Long id, String image, List<ImageVariantDTO> imageVariants){
            this.id = id;
            this.image = image;
            this.imageVariants = imageVariants;
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
    }

    public static class ImageVariantDTO {
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
