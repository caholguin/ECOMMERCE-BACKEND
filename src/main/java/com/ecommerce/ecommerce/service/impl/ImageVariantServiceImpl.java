package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.entity.ImageVariant;
import com.ecommerce.ecommerce.entity.Variant;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.repository.ImageVariantRepository;
import com.ecommerce.ecommerce.service.ImageVariantService;
import com.ecommerce.ecommerce.service.VariantService;
import org.springframework.stereotype.Service;

@Service
public class ImageVariantServiceImpl implements ImageVariantService {

    private final ImageVariantRepository imageVariantRepository;
    private final VariantService variantService;

    public ImageVariantServiceImpl(ImageVariantRepository imageVariantRepository, VariantService variantService){
        this.imageVariantRepository = imageVariantRepository;
        this.variantService = variantService;
    }

    @Override
    public void delete(Long id){
        ImageVariant image = this.findByIdEntity(id);
        imageVariantRepository.delete(image);
    }

    @Override
    public ImageVariant findByIdEntity(Long id){
        return imageVariantRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Imagen con Id " + id + " no encontrada"));
    }

    @Override
    public void save(Long id, String url){

        Variant variant = this.variantService.findByIdEntity(id);

        ImageVariant imageVariant = new ImageVariant();
        imageVariant.setVariant(variant);
        imageVariant.setUrl(url);

        this.imageVariantRepository.save(imageVariant);
    }
}
