package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feature_variant")
public class FeatureVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    public FeatureVariant(){
    }

    public FeatureVariant(Long id, Variant variant, Feature feature){
        this.id = id;
        this.variant = variant;
        this.feature = feature;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Variant getVariant(){
        return variant;
    }

    public void setVariant(Variant variant){
        this.variant = variant;
    }

    public Feature getFeature(){
        return feature;
    }

    public void setFeature(Feature feature){
        this.feature = feature;
    }

    @Override
    public String toString(){
        return "FeatureVariant{" +
                "id=" + id +
                ", variant=" + variant +
                ", feature=" + feature +
                '}';
    }
}
