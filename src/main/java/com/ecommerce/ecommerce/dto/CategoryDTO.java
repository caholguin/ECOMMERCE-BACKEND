package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Family;
import com.ecommerce.ecommerce.entity.Subcategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CategoryDTO {

    private Long id;

    private String name;

    private Family family;

    //private List<Subcategory> subCategories;


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

    public Family getFamily(){
        return family;
    }

    public void setFamily(Family family){
        this.family = family;
    }
}
