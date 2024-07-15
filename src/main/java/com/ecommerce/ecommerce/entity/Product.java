package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String detail;

    private String image;

    private Double price;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Variant> variants;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OptionProduct> optionProducts;

    public Product(){
    }

    public Product(Long id, String name, String detail, String image, Double price, int stock, SubCategory subCategory, List<Variant> variants, List<OptionProduct> optionProducts){
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.subCategory = subCategory;
        this.variants = variants;
        this.optionProducts = optionProducts;
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

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public SubCategory getSubCategory(){
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory){
        this.subCategory = subCategory;
    }

    public List<Variant> getVariants(){
        return variants;
    }

    public void setVariants(List<Variant> variants){
        this.variants = variants;
    }

    public List<OptionProduct> getOptionProducts(){
        return optionProducts;
    }

    public void setOptionProducts(List<OptionProduct> optionProducts){
        this.optionProducts = optionProducts;
    }

    @Override
    public String toString(){
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", subCategory=" + subCategory +
                ", variants=" + variants +
                ", optionProducts=" + optionProducts +
                '}';
    }
}
