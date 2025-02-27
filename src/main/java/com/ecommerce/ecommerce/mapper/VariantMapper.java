package com.ecommerce.ecommerce.mapper;
import com.ecommerce.ecommerce.dto.request.SaveVariantDTO;
import com.ecommerce.ecommerce.dto.response.ImageVariantDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.entity.Variant;
import java.util.List;
import java.util.stream.Collectors;

public class VariantMapper {

    public static VariantDTO toDto(Variant variant){

        if(variant == null) return null;

        VariantDTO variantDTO = new VariantDTO();
        variantDTO.setId(variant.getId());
        variantDTO.setImage(variant.getImage());
        variantDTO.setStock(variant.getStock());
        variantDTO.setImagesVariant(ImageVariantMapper.toImagesVariantDto(variant.getImagesVariant()));
        variantDTO.setFeatureVariants(FeatureVariantMapper.toFeaturesVariantListDTO(variant.getFeatureVariants()));

        return variantDTO;
    }

    public static  List<VariantDTO> toDtoList(List<Variant> variants){
        if(variants == null) return null;

        return variants.stream().map(VariantMapper::toDto).collect(Collectors.toList());
    }


    public static ProductDTO.VariantDTO toVariantProductDto(Variant variant){
        if (variant == null ) return null;

        List<ProductDTO.ImageVariantDTO> imageDTOs = variant.getImagesVariant().stream()
                .map(ImageVariantMapper::toImageVariantDTO)
                .collect(Collectors.toList());

        List<ProductDTO.FeatureVariantDTO> featureVariantDTO = variant.getFeatureVariants().stream()
                .map(FeatureVariantMapper::toFeatureVariantDTO)
                .collect(Collectors.toList());

        return new ProductDTO.VariantDTO(
                variant.getId(),
                variant.getImage(),
                variant.getStock(),
                imageDTOs,
                featureVariantDTO
        );
    }

    public static List<ProductDTO.VariantDTO> toVariantsProductDto(List<Variant> variants){
        if (variants == null ) return null;

        return variants.stream()
                .map(VariantMapper::toVariantProductDto)
                .toList();
    }

    public static ImageVariantDTO.VariantDTO toImageVariantDto(Variant variant){
        if (variant == null ) return null;

        return new ImageVariantDTO.VariantDTO(
                variant.getId(),
                variant.getImage()
        );
    }


    public static void updateEntity(Variant variant, SaveVariantDTO saveVariantDTO){
        if(variant == null || saveVariantDTO == null) return;

        variant.setImage(saveVariantDTO.getImage());
        variant.setStock(saveVariantDTO.getStock());

    }

}
