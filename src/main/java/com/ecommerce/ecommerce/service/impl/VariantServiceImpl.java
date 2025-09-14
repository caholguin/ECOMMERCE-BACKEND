package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveVariantDTO;
import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.entity.*;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.VariantMapper;
import com.ecommerce.ecommerce.repository.ImageVariantRepository;
import com.ecommerce.ecommerce.repository.VariantRepository;
import com.ecommerce.ecommerce.service.VariantService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantServiceImpl implements VariantService {

    private final VariantRepository variantRepository;
    private final ImageVariantRepository imageVariantRepository;

    public VariantServiceImpl(VariantRepository variantRepository, ImageVariantRepository imageVariantRepository){
        this.variantRepository = variantRepository;
        this.imageVariantRepository = imageVariantRepository;
    }

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
    public VariantDTO findById(Long id){
        return VariantMapper.toDto(this.findByIdEntity(id));
    }

    @Override
    public String addMedia(Long id, String url) {
        Variant variant = this.findByIdEntity(id);

        ImageVariant imageVariant = new ImageVariant();
        imageVariant.setUrl(url);
        imageVariant.setVariant(variant);

        imageVariantRepository.save(imageVariant);
        return url;
    }

    @Override
    public VariantDTO updateStock(Long id, SaveVariantDTO saveVariantDTO){
        Variant variant = this.findByIdEntity(id);
        VariantMapper.updateEntity(variant, saveVariantDTO);
        return VariantMapper.toDto(variantRepository.save(variant));
    }

    @Override
    public Variant findByIdEntity(Long id){
        return variantRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Variante con ID: " + id + " no encontrada"));
    }

    @Override
    @Transactional
    public Void deleteByProductId(Long id){
      variantRepository.deleteByProductId(id);;
      return null;
    }

    @Override
    public VariantDTO findVariantsByFeatures(Long productId, List<Long> featureIds){
        return VariantMapper.toDto(variantRepository.findVariantsByExactFeatures(productId, featureIds, (long) featureIds.size()));
    }

    @Override
    public VariantDTO discountStock(Long id, int quantity) {
        Variant variant = this.findByIdEntity(id);

        if (variant.getStock() < quantity) {
            throw new RuntimeException("No hay suficiente stock para la variante id" + id);
        }

        variant.setStock(variant.getStock() - quantity);
        Variant updatedVariant = variantRepository.save(variant);

        return VariantMapper.toDto(updatedVariant);
    }


}
