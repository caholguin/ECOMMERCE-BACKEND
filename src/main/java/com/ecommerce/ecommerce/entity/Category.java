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

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Subcategory> subCategories;

}
