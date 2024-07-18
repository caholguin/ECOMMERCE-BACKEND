package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.OptionDTO;
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
}
