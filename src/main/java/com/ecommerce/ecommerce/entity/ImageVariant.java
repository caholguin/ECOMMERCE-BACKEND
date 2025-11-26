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

    //@Column(name = "is_default")
    private Boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    public ImageVariant(Long id, String url, Boolean isDefault, Variant variant){
        this.id = id;
        this.url = url;
        this.isDefault = isDefault;
        this.variant = variant;
    }

    public ImageVariant(Long id, String url, Boolean isDefault){
        this.id = id;
        this.url = url;
        this.isDefault = isDefault;
    }

    public ImageVariant(){

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

    public boolean isDefault(){
        return isDefault;
    }

    public void setDefault(boolean aDefault){
        isDefault = aDefault;
    }

    public Variant getVariant(){
        return variant;
    }

    public void setVariant(Variant variant){
        this.variant = variant;
    }
}

