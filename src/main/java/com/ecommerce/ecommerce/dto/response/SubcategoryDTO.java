package com.ecommerce.ecommerce.dto.response;


import java.io.Serializable;
import java.util.List;

public class SubcategoryDTO {

    private Long id;

    private String name;

    private CategoryDTO category;

    private List<ProductDTO> products;

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

    public CategoryDTO getCategory(){
        return category;
    }

    public void setCategory(CategoryDTO category){
        this.category = category;
    }

    public List<ProductDTO> getProducts(){
        return products;
    }

    public void setProducts(List<ProductDTO> products){
        this.products = products;
    }

    public static class CategoryDTO implements Serializable {
        private Long id;
        private String name;
        private String icon;

        public CategoryDTO(Long id, String name, String icon){
            this.id = id;
            this.name = name;
            this.icon = icon;
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

        public String getIcon(){
            return icon;
        }

        public void setIcon(String icon){
            this.icon = icon;
        }
    }

    public static class ProductDTO implements Serializable {

        private Long id;
        private String name;
        private String detail;
        private String image;
        private Double price;

        public ProductDTO(Long id, String name, String detail, String image, Double price){
            this.id = id;
            this.name = name;
            this.detail = detail;
            this.image = image;
            this.price = price;
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
    }
}
