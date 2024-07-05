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
}
