package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "option_product")
public class OptionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String features;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;

    public OptionProduct(){
    }

    public OptionProduct(Long id, String features, int stock, Product product, Option option){
        this.id = id;
        this.features = features;
        this.stock = stock;
        this.product = product;
        this.option = option;
    }

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

    public int getStock(){
        return stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public Product getProduct(){
        return product;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public Option getOption(){
        return option;
    }

    public void setOption(Option option){
        this.option = option;
    }

    @Override
    public String toString(){
        return "OptionProduct{" +
                "id=" + id +
                ", features='" + features + '\'' +
                ", stock=" + stock +
                ", product=" + product +
                ", option=" + option +
                '}';
    }
}
