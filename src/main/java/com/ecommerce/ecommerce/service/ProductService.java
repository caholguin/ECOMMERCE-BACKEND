package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Page<ProductDTO> findAll(Pageable pageable);

    ProductDTO save(ProductDTO productDTO);

    Optional<ProductDTO> findById(Long id);

    ProductDTO update(Long id, ProductDTO productDTO);

    ProductDTO delete(Long id);
}
