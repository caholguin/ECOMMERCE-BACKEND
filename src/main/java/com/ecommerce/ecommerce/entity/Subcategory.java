package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subcategories")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<Product> products;

    public Subcategory(){
    }

    public Subcategory(Long id, String name, Category category, List<Product> products){
        this.id = id;
        this.name = name;
        this.category = category;
        this.products = products;
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

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public List<Product> getProducts(){
        return products;
    }

    public void setProducts(List<Product> products){
        this.products = products;
    }

    @Override
    public String toString(){
        return "Subcategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", products=" + products +
                '}';
    }
}
