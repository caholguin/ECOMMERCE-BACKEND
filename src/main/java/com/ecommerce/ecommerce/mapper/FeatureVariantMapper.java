package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.entity.FeatureVariant;
import com.ecommerce.ecommerce.entity.Variant;

public class FeatureVariantMapper {


    public VariantDTO toDto(Variant variant){

        if(variant == null) return null;

        VariantDTO variantDTO = new VariantDTO();
        variantDTO.setId(variant.getId());
        variantDTO.setImage(variant.getImage());
        variantDTO.setImagesVariant(ImageVariantMapper.toImagesVariantDto(variant.getImagesVariant()));

        return variantDTO;
    }

    public static ProductDTO.FeatureVariantDTO toFeatureVariantDTO(FeatureVariant featureVariant) {

        if (featureVariant == null) return null;

        FeatureDTO featureDTO = FeatureMapper.toDTO(featureVariant.getFeature());

        return new ProductDTO.FeatureVariantDTO(
                featureVariant.getId(),
                featureDTO
        );
    }
}
