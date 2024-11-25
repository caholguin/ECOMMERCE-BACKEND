package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.FeatureDTO;
import com.ecommerce.ecommerce.dto.OptionDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.entity.Option;
import org.springframework.stereotype.Component;

@Component
public class FeatureMapper {

    public static FeatureDTO toDTO(Feature feature) {

        if(feature == null) return null;

        FeatureDTO featureDTO = new FeatureDTO();

        featureDTO.setId(feature.getId());
        featureDTO.setDescription(feature.getDescription());
        featureDTO.setValue(feature.getValue());

        if (feature.getOption() != null){
            Option option = feature.getOption();
            OptionDTO optionDTO = new OptionDTO();
            optionDTO.setId(option.getId());
            optionDTO.setName(option.getName());
            optionDTO.setType(option.getType());
            featureDTO.setOption(optionDTO);
        }

        return featureDTO;
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
}
