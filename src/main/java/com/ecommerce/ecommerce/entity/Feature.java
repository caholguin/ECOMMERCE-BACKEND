package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "features")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;

    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL)
    private List<FeatureVariant> featureVariants;

    public Feature(){
    }

    public Feature(Long id, String name, Option option, List<FeatureVariant> featureVariants){
        this.id = id;
        this.name = name;
        this.option = option;
        this.featureVariants = featureVariants;
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

    public Option getOption(){
        return option;
    }

    public void setOption(Option option){
        this.option = option;
    }

    public List<FeatureVariant> getFeatureVariants(){
        return featureVariants;
    }

    public void setFeatureVariants(List<FeatureVariant> featureVariants){
        this.featureVariants = featureVariants;
    }
}
