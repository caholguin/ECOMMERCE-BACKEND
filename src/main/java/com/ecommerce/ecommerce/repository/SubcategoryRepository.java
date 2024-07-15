package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
