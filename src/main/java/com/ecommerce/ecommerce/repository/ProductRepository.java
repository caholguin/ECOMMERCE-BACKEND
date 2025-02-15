package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Page<Product> findBySubCategoryId(Long subCategoryId, Pageable pageable);
}
