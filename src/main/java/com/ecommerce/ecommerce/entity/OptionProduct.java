package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "option_product")
public class OptionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String features;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;
}
