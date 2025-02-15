package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.request.SaveOptionDTO;
import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.dto.response.OptionDTO;
import com.ecommerce.ecommerce.dto.response.OptionProductDTO;
import com.ecommerce.ecommerce.entity.Option;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionMapper {

    public static OptionDTO toDto(Option option){

        if (option == null) return null;

        OptionDTO optionDTO = new OptionDTO();
        optionDTO.setId(option.getId());
        optionDTO.setName(option.getName());
        optionDTO.setType(option.getType());
        optionDTO.setFeatures(FeatureMapper.toFeaturestoOption(option.getFeatures()));

        return optionDTO;
    }

    public static List<OptionDTO> toDtoList(List<Option> options){

        if (options == null) return null;

        return options.stream()
                .map(OptionMapper::toDto)
                .toList();
    }

    public static Option toEntity(SaveOptionDTO saveOptionDTO){
        if (saveOptionDTO == null) return null;

        Option option = new Option();
        option.setId(saveOptionDTO.getId());
        option.setName(saveOptionDTO.getName());
        option.setType(saveOptionDTO.getType());

        return option;
    }

    public static void updateEntity(Option option, SaveOptionDTO saveOptionDTO){
        if (option == null || saveOptionDTO == null) return;

        option.setName(saveOptionDTO.getName());

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
