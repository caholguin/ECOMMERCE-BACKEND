package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.OptionDTO;
import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.entity.Option;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper {

    public OptionDTO toDTO(Option option){

        OptionDTO optionDTO = new OptionDTO();

       optionDTO.setId(option.getId());
       optionDTO.setName(option.getName());
       optionDTO.setType(option.getType());

        return optionDTO;
    }

    public static FeatureDTO.OptionDTO toGetOptionDTO(Option option){
        if(option == null) return null;

        return new FeatureDTO.OptionDTO(
                option.getId(),
                option.getName()
        );
    }



    /*
    *
    *
    * public static CategoryDTO.FamilyDTO toGetFamilyDto(Family family){
        if (family == null ) return null;

        return new CategoryDTO.FamilyDTO(
                family.getId(),
                family.getName()
        );

    }
    * */
}
