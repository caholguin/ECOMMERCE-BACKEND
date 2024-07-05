package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "families")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Category> categories;
}
