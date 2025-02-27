package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.dto.response.FeatureVariantDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.entity.FeatureVariant;
import com.ecommerce.ecommerce.entity.ImageVariant;

import java.util.List;

public class FeatureVariantMapper {


    public FeatureVariantDTO toDto(FeatureVariant featureVariant){

        if(featureVariant == null) return null;

        FeatureVariantDTO featureVariantDTO = new FeatureVariantDTO();
        featureVariantDTO.setFeature(featureVariant.getFeature());

        return featureVariantDTO;
    }

    public static ProductDTO.FeatureVariantDTO toFeatureVariantDTO(FeatureVariant featureVariant) {

        if (featureVariant == null) return null;

        FeatureDTO featureDTO = FeatureMapper.toDto(featureVariant.getFeature());

        return new ProductDTO.FeatureVariantDTO(
                featureVariant.getId(),
                featureDTO
        );
    }

    public static VariantDTO.FeatureVariantDTO toFeaturesVariantDTO(FeatureVariant featureVariant){
        if (featureVariant == null) return null;

        FeatureDTO featureDTO = FeatureMapper.toDto(featureVariant.getFeature());

        return new VariantDTO.FeatureVariantDTO(
                featureVariant.getId(),
                featureDTO
        );
    }

    public static List<VariantDTO.FeatureVariantDTO> toFeaturesVariantListDTO(List<FeatureVariant> featuresVariant){
        if (featuresVariant == null ) return null;

        return featuresVariant.stream()
                .map(FeatureVariantMapper::toFeaturesVariantDTO)
                .toList();
    }
}
