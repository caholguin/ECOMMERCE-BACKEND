package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.entity.Variant;
import com.ecommerce.ecommerce.repository.VariantRepository;
import com.ecommerce.ecommerce.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl implements VariantService {

    @Autowired
    private VariantRepository variantRepository;

    @Override
    public List<Variant> findByProductoId(Long productId){
        return variantRepository.findByProductId(productId);
    }
}
