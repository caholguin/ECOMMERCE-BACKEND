package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String detail;

    private String image;

    private Double price;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private Subcategory subCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Variant> variants;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OptionProduct> optionProducts;
}
