package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.FeatureVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureVariantRepository extends JpaRepository<FeatureVariant, Long> {

    boolean existsByVariantIdAndFeatureId(Long variantId, Long featureId);

}
