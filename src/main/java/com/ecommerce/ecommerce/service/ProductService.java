package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.request.ProductSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Page<ProductDTO> findAll(ProductSearchDTO productSearchDTO, Pageable pageable);

    ProductDTO save(ProductDTO productDTO);

    Optional<ProductDTO> findById(Long id);

    ProductDTO update(Long id, ProductDTO productDTO);

    ProductDTO delete(Long id);

    String addMedia(Long id, String url);
}
