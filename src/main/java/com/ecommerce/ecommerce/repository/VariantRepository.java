package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {

    List<Variant> findByProductId(Long productId);

    void deleteByProductId(Long productId);

    @Query("""
        SELECT v FROM Variant v
        JOIN v.featureVariants fv
        JOIN v.product p
        WHERE p.id = :productId
        AND fv.feature.id IN :featureIds
        GROUP BY v.id
        HAVING COUNT(DISTINCT fv.feature.id) = :size
    """)
    Variant findVariantsByExactFeatures(@Param("productId") Long productId,
                                              @Param("featureIds") List<Long> featureIds,
                                              @Param("size") Long size);

}
