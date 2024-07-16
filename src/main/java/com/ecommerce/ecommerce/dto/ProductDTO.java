package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;

    @NotBlank(message = "El campo detalle es obligatorio")
    private String detail;

    private String image;

    @PositiveOrZero(message = "El campo precio es obligatorio")
    @NotNull(message = "El campo precio es obligatorio")
    private Double price;

    private int stock;

    @NotNull(message = "El campo subcategoría es obligatorio")
    private Long subCategoryId;

    private SubcategoryDTO subCategory;

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

    public Long getSubCategoryId(){
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId){
        this.subCategoryId = subCategoryId;
    }

    public SubcategoryDTO getSubCategory(){
        return subCategory;
    }

    public void setSubCategory(SubcategoryDTO subCategory){
        this.subCategory = subCategory;
    }
}
