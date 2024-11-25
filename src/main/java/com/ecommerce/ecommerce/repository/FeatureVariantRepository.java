package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.FeatureVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureVariantRepository extends JpaRepository<FeatureVariant, Long> {

    @Query("SELECT CASE WHEN COUNT(fv) > 0 THEN true ELSE false END " +
            "FROM FeatureVariant fv " +
            "WHERE fv.variant.id = :variantId AND fv.feature.id = :featureId")
    boolean existsByVariantIdAndFeatureId(Long variantId, Long featureId);

}
