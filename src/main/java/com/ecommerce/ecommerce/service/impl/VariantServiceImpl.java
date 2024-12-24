package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.entity.ImageVariant;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.Variant;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.CategoryMapper;
import com.ecommerce.ecommerce.mapper.VariantMapper;
import com.ecommerce.ecommerce.repository.ImageVariantRepository;
import com.ecommerce.ecommerce.repository.VariantRepository;
import com.ecommerce.ecommerce.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VariantServiceImpl implements VariantService {

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private ImageVariantRepository imageVariantRepository;

    @Override
    public List<Variant> findByProductoId(Long productId){
        return variantRepository.findByProductId(productId);
    }

    @Override
    public List<VariantDTO> findAll(){
        List<Variant> variants = variantRepository.findAll();
        return VariantMapper.toDtoList(variants);
    }

    @Override
    public String addMedia(Long id, String url) {
        Optional<Variant> variantOptional = variantRepository.findById(id);

        if (variantOptional.isEmpty()) {
            throw new ObjectNotFoundException("No existe una variante con el id: " + id);
        }

        Variant variant = variantOptional.get();

        ImageVariant imageVariant = new ImageVariant();
        imageVariant.setUrl(url);
        imageVariant.setVariant(variant);

        imageVariantRepository.save(imageVariant);
        return url;
    }

}
