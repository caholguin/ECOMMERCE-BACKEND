package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "variants")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private List<FeatureVariant> featureVariants;

}
