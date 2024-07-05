package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private List<Feature> features;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private List<OptionProduct> optionProducts;

    public Option(){
    }

    public Option(Long id, String name, String type, List<Feature> features, List<OptionProduct> optionProducts){
        this.id = id;
        this.name = name;
        this.type = type;
        this.features = features;
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

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public List<Feature> getFeatures(){
        return features;
    }

    public void setFeatures(List<Feature> features){
        this.features = features;
    }

    public List<OptionProduct> getOptionProducts(){
        return optionProducts;
    }

    public void setOptionProducts(List<OptionProduct> optionProducts){
        this.optionProducts = optionProducts;
    }

    @Override
    public String toString(){
        return "Option{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", features=" + features +
                ", optionProducts=" + optionProducts +
                '}';
    }
}
