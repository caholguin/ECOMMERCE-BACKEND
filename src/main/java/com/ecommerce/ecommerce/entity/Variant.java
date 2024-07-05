package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "variants")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private List<FeatureVariant> featureVariants;

    public Variant(){
    }

    public Variant(Long id, String image, Product product, List<FeatureVariant> featureVariants){
        this.id = id;
        this.image = image;
        this.product = product;
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

    public Product getProduct(){
        return product;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public List<FeatureVariant> getFeatureVariants(){
        return featureVariants;
    }

    public void setFeatureVariants(List<FeatureVariant> featureVariants){
        this.featureVariants = featureVariants;
    }

    @Override
    public String toString(){
        return "Variant{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", product=" + product +
                ", featureVariants=" + featureVariants +
                '}';
    }
}
