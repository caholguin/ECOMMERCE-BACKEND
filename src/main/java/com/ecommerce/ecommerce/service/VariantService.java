package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.entity.Variant;

import java.util.List;

public interface VariantService {

    List<Variant> findByProductoId(Long productId);

    List<VariantDTO> findAll();

    String addMedia(Long id, String url);
}
