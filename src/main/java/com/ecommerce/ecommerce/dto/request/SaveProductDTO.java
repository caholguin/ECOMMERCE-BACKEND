package com.ecommerce.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class SaveProductDTO implements Serializable {

    private Long id;
    @NotBlank(message = "El campo nombre es obligatorio")
    private String name;
    private String detail;
    private String image;
    @NotNull(message = "El campo precio es obligatorio")
    private Double price;
    private int stock;
    private int status;
    @NotNull(message = "El campo subcategoría es obligatorio")
    private Long subcategoryId;

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

    public Long getSubcategoryId(){
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId){
        this.subcategoryId = subcategoryId;
    }
}
