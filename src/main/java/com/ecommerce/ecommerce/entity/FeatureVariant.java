package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feature_variant")
public class FeatureVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "variant_id", nullable = false)
    private Variant variant;

    @ManyToOne
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;
}
