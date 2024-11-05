package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "images_variants")
public class ImageVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String url;

    private Integer orderItems;

    @ManyToOne
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    public ImageVariant(){
    }

    public ImageVariant(Long id, String url, Integer orderItems, Variant variant){
        this.id = id;
        this.url = url;
        this.orderItems = orderItems;
        this.variant = variant;
    }

    public ImageVariant(Long id, String url, Integer orderItems){
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

    public Variant getVariant(){
        return variant;
    }

    public void setVariant(Variant variant){
        this.variant = variant;
    }
}

