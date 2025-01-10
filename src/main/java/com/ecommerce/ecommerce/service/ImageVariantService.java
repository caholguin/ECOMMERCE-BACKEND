package com.ecommerce.ecommerce.service;
import com.ecommerce.ecommerce.dto.response.ImageVariantDTO;
import com.ecommerce.ecommerce.entity.Family;
import com.ecommerce.ecommerce.entity.ImageVariant;

public interface ImageVariantService {

    void delete(Long id);

    ImageVariant findByIdEntity(Long id);
}
