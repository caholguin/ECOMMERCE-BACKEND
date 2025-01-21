package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveProductDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.request.ProductSearchDTO;
import com.ecommerce.ecommerce.entity.Option;
import com.ecommerce.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<ProductDTO> findAll(ProductSearchDTO productSearchDTO, Pageable pageable);

    ProductDTO save(SaveProductDTO saveProductDTO);

    ProductDTO findById(Long id);

    ProductDTO update(Long id, SaveProductDTO saveProductDTO);

    void delete(Long id);

    String addMedia(Long id, String url);

    Product findByIdEntity(Long id);

    List<List<Long>> triggerVariants(Long productId);
}
