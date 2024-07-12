package com.ecommerce.ecommerce.entity;

import com.ecommerce.ecommerce.dto.FamilyDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Category> categories;

    public Family(){
    }

    public Family(Long id, String name, List<Category> categories){
        this.id = id;
        this.name = name;
        this.categories = categories;
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

    public List<Category> getCategories(){
        return categories;
    }

    public void setCategories(List<Category> categories){
        this.categories = categories;
    }

    @Override
    public String toString(){
        return "Family{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }
}
