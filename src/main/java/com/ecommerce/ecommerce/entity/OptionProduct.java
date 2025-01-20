package com.ecommerce.ecommerce.entity;

import com.ecommerce.ecommerce.utils.JsonConverter;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "option_product")
public class OptionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = JsonConverter.class)
    private List<Map<String, String>> features;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;

    public OptionProduct(){
    }

    public OptionProduct(Long id, List<Map<String, String>> features, Product product, Option option){
        this.id = id;
        this.features = features;
        this.product = product;
        this.option = option;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public List<Map<String, String>> getFeatures(){
        return features;
    }

    public void setFeatures(List<Map<String, String>> features){
        this.features = features;
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
}
