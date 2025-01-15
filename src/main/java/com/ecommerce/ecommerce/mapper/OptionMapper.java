package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.dto.response.OptionDTO;
import com.ecommerce.ecommerce.dto.response.OptionProductDTO;
import com.ecommerce.ecommerce.entity.Option;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionMapper {

    public static OptionDTO toDto(Option option){

        OptionDTO optionDTO = new OptionDTO();

       optionDTO.setId(option.getId());
       optionDTO.setName(option.getName());
       optionDTO.setType(option.getType());
       optionDTO.setFeatures(FeatureMapper.toFeaturestoOption(option.getFeatures()));

        return optionDTO;
    }

    public static FeatureDTO.OptionDTO toGetOptionDTO(Option option){
        if(option == null) return null;

        return new FeatureDTO.OptionDTO(
                option.getId(),
                option.getName()
        );
    }

    public static OptionProductDTO.OptionDTO toGetOptionProductDTO(Option option){
        if(option == null) return null;

        return new OptionProductDTO.OptionDTO(
                option.getId(),
                option.getName()
        );
    }
}
