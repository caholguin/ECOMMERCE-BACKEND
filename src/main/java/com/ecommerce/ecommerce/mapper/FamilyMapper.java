package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.FamilyDTO;
import com.ecommerce.ecommerce.entity.Family;
import org.springframework.stereotype.Component;

@Component
public class FamilyMapper {

    public FamilyDTO toDTO(Family family){

        FamilyDTO familyDTO = new FamilyDTO();

       familyDTO.setId(family.getId());
       familyDTO.setName(family.getName());

        return familyDTO;
    }

    public Family toEntity(FamilyDTO familyDTO){

        Family family = new Family();

       family.setId(familyDTO.getId());
       family.setName(familyDTO.getName());

        return family;
    }
}
