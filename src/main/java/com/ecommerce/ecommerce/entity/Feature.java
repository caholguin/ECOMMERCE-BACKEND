package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "features")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private String description;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;

    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL)
    private List<FeatureVariant> featureVariants;
}
