package com.ecommerce.ecommerce.mapper;


import com.ecommerce.ecommerce.dto.response.FamilyDTO;
import com.ecommerce.ecommerce.entity.Family;
import org.springframework.stereotype.Component;



@Component
public class FamilyMapper {


    public static FamilyDTO toDTO(Family family){

        if(family == null) return null;

        FamilyDTO familyDTO = new FamilyDTO();

        familyDTO.setId(family.getId());
        familyDTO.setName(family.getName());
        familyDTO.setCategories(CategoryMapper.toGetFamilyCategoriesDTO(family.getCategories()));

        return familyDTO;
    }

    public Family toEntity(FamilyDTO familyDTO){

        Family family = new Family();

        family.setId(familyDTO.getId());
        family.setName(familyDTO.getName());

        return family;
    }
}
