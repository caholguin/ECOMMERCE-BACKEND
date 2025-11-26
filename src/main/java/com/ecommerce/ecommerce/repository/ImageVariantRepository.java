package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.ImageVariant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ImageVariantRepository extends JpaRepository<ImageVariant, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ImageVariant v SET v.isDefault = false WHERE v.variant.id = :variantId")
    //@Query("UPDATE Address a SET a.isDefault = false WHERE a.user.id = :userId")
    void clearDefaultForVariantId(Long variantId);
}
