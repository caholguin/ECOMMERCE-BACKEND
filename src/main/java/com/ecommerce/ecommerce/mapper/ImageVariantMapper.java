package com.ecommerce.ecommerce.mapper;
import com.ecommerce.ecommerce.dto.response.ImageVariantDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.entity.ImageVariant;

import java.util.List;

public class ImageVariantMapper {

    public static ImageVariantDTO toDto(ImageVariant imageVariant) {

        if (imageVariant == null) return null;

        ImageVariantDTO imageVariantDTO = new ImageVariantDTO();
        imageVariantDTO.setId(imageVariant.getId());
        imageVariantDTO.setUrl(imageVariant.getUrl());
        imageVariantDTO.getDefault(imageVariant.isDefault());
        imageVariantDTO.setVariant(VariantMapper.toImageVariantDto(imageVariant.getVariant()));

        return imageVariantDTO;
    }

    public static VariantDTO.ImageVariantDTO toImagesVariantDto(ImageVariant imageVariant) {
        if (imageVariant == null) return null;

        return new VariantDTO.ImageVariantDTO(
                imageVariant.getId(),
                imageVariant.getUrl(),
                imageVariant.isDefault()
        );
    }

    public static List<VariantDTO.ImageVariantDTO> toImagesVariantDto(List<ImageVariant> imagesVariant){
        if (imagesVariant == null ) return null;

        return imagesVariant.stream()
                .map(ImageVariantMapper::toImagesVariantDto)
                .toList();
    }

    public static ProductDTO.ImageVariantDTO toImageVariantDTO(ImageVariant imageVariant) {
        if (imageVariant == null) return null;

        return new ProductDTO.ImageVariantDTO(
                imageVariant.getId(),
                imageVariant.getUrl(),
                imageVariant.isDefault()
        );
    }

}
