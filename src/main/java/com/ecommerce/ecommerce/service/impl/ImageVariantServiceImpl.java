package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.response.ImageVariantDTO;
import com.ecommerce.ecommerce.entity.ImageVariant;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.repository.ImageVariantRepository;
import com.ecommerce.ecommerce.service.ImageVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageVariantServiceImpl implements ImageVariantService {

    @Autowired
    private ImageVariantRepository imageVariantRepository;

    @Override
    public void delete(Long id){
        ImageVariant image = this.findByIdEntity(id);
        imageVariantRepository.delete(image);

    }

    @Override
    public ImageVariant findByIdEntity(Long id){
        return imageVariantRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Imagen con Id " + id + " no encontrada"));
    }

}
