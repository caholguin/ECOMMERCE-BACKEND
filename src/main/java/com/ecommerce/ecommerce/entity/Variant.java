package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "variants")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = true)
    private String image;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private List<FeatureVariant> featureVariants;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private List<ImageVariant> imagesVariant;

    public Variant(){
    }

    public Variant(Long id, String image, int stock, Product product, List<FeatureVariant> featureVariants, List<ImageVariant> imagesVariant){
        this.id = id;
        this.image = image;
        this.stock = stock;
        this.product = product;
        this.featureVariants = featureVariants;
        this.imagesVariant = imagesVariant;
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

    public List<FeatureVariant> getFeatureVariants(){
        return featureVariants;
    }

    public void setFeatureVariants(List<FeatureVariant> featureVariants){
        this.featureVariants = featureVariants;
    }

    public List<ImageVariant> getImagesVariant(){
        return imagesVariant;
    }

    public void setImagesVariant(List<ImageVariant> imagesVariant){
        this.imagesVariant = imagesVariant;
    }

    @Override
    public String toString(){
        return "Variant{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", stock=" + stock +
                ", product=" + product +
                ", featureVariants=" + featureVariants +
                ", imagesVariant=" + imagesVariant +
                '}';
    }
}
