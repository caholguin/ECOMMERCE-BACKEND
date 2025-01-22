package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.request.SaveFeatureDTO;
import com.ecommerce.ecommerce.dto.request.SaveOptionDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.dto.response.OptionDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.entity.Option;
import com.ecommerce.ecommerce.entity.SubCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeatureMapper {

    public static FeatureDTO toDto(Feature feature) {

        if(feature == null) return null;

        FeatureDTO featureDTO = new FeatureDTO();
        featureDTO.setId(feature.getId());
        featureDTO.setName(feature.getName());
        featureDTO.setOption(OptionMapper.toGetOptionDTO(feature.getOption()));

        return featureDTO;
    }

    public static Feature toEntity(SaveFeatureDTO saveFeatureDTO, Option option) {
        if(saveFeatureDTO == null) return null;

        Feature feature = new Feature();
        feature.setId(saveFeatureDTO.getId());
        feature.setName(saveFeatureDTO.getName());
        feature.setOption(option);

        return feature;
    }


    public static void updateEntity(Feature feature, SaveFeatureDTO saveFeatureDTO, Option option){
        if (feature == null || saveFeatureDTO == null) return;

        feature.setName(saveFeatureDTO.getName());
        feature.setOption(option);

    }

    public static CategoryDTO toDto(Category category){

        if(category == null) return null;

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setIcon(category.getIcon());
        categoryDTO.setFamily(FamilyMapper.toGetFamilyDto(category.getFamily()));
        categoryDTO.setSubCategories(SubCategoryMapper.toSubCategoriesCategoriesDto(category.getSubCategories()));

        return categoryDTO;
    }

    public static OptionDTO.FeatureDTO toFeaturetoOption(Feature feature){

        if(feature == null) return null;

        return new OptionDTO.FeatureDTO(
                feature.getId(),
                feature.getName()
        );

    }

    public static List<OptionDTO.FeatureDTO> toFeaturestoOption(List<Feature> feature){
        if (feature == null) return null;

        return feature.stream()
                .map(FeatureMapper::toFeaturetoOption)
                .toList();
    }
}
