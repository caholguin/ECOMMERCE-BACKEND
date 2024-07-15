package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long family_id;

    @ManyToOne
    @JoinColumn(name = "family_id", insertable = false, updatable = false)
    private Family family;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Subcategory> subCategories;

    public Category(){
    }

    public Category(Long id, String name, Family family, List<Subcategory> subCategories){
        this.id = id;
        this.name = name;
        this.family = family;
        this.subCategories = subCategories;
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

    public Long getFamily_id(){
        return family_id;
    }

    public void setFamily_id(Long family_id){
        this.family_id = family_id;
    }

    public Family getFamily(){
        return family;
    }

    public void setFamily(Family family){
        this.family = family;
    }

    public List<Subcategory> getSubCategories(){
        return subCategories;
    }

    public void setSubCategories(List<Subcategory> subCategories){
        this.subCategories = subCategories;
    }

    @Override
    public String toString(){
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family=" + family +
                ", subCategories=" + subCategories +
                '}';
    }
}
