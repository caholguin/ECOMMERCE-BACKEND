package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.FeatureDTO;
import com.ecommerce.ecommerce.dto.OptionDTO;
import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.entity.Option;
import org.springframework.stereotype.Component;

@Component
public class FeatureMapper {

    public FeatureDTO toDTO(Feature feature) {

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
}
