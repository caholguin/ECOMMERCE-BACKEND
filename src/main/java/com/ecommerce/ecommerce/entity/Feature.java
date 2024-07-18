package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "features")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private String description;

    @Column(name = "option_id",nullable = false)
    private Long optionId;

    @ManyToOne
    @JoinColumn(name = "option_id", insertable = false, updatable = false)
    private Option option;

    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL)
    private List<FeatureVariant> featureVariants;

    public Feature(){
    }

    public Feature(Long id, String value, String description, Long optionId, Option option, List<FeatureVariant> featureVariants){
        this.id = id;
        this.value = value;
        this.description = description;
        this.optionId = optionId;
        this.option = option;
        this.featureVariants = featureVariants;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Long getOptionId(){
        return optionId;
    }

    public void setOptionId(Long optionId){
        this.optionId = optionId;
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

    @Override
    public String toString(){
        return "Feature{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                ", option=" + option +
                ", featureVariants=" + featureVariants +
                '}';
    }
}
