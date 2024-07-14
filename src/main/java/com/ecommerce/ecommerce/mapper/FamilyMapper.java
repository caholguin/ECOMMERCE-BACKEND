package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.dto.FamilyDTO;
import com.ecommerce.ecommerce.entity.Family;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FamilyMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    public FamilyDTO toDTO(Family family){

        FamilyDTO familyDTO = new FamilyDTO();

        familyDTO.setId(family.getId());
        familyDTO.setName(family.getName());
        if (family.getCategories() != null) {
            familyDTO.setCategories(family.getCategories().stream().map(categoryMapper::toSimpleDTO).collect(Collectors.toList()));
        }

        return familyDTO;
    }

    public Family toEntity(FamilyDTO familyDTO){

        Family family = new Family();

        family.setId(familyDTO.getId());
        family.setName(familyDTO.getName());

        return family;
    }
}
