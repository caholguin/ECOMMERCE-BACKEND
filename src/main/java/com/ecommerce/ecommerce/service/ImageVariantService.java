package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.entity.ImageVariant;

public interface ImageVariantService {

    void delete(Long id);

    ImageVariant findByIdEntity(Long id);

    void save(Long id, String url);

    void setDefaultVariant(Long id);
}
