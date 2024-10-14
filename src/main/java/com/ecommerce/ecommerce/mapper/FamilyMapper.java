package com.ecommerce.ecommerce.mapper;
import com.ecommerce.ecommerce.dto.request.SaveFamilyDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.dto.response.FamilyDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Family;
import org.springframework.stereotype.Component;

@Component
public class FamilyMapper {


    public static FamilyDTO toDto(Family family){

        if(family == null) return null;

        FamilyDTO familyDTO = new FamilyDTO();
        familyDTO.setId(family.getId());
        familyDTO.setName(family.getName());
        familyDTO.setCategories(CategoryMapper.toFamilyCategoriesDTO(family.getCategories()));

        return familyDTO;
    }

    public static Family toEntity(SaveFamilyDTO saveFamilyDTO){

        if(saveFamilyDTO == null) return null;

        Family family = new Family();
        family.setId(saveFamilyDTO.getId());
        family.setName(saveFamilyDTO.getName());

        return family;
    }


    public static void updateEntity(Family family, SaveFamilyDTO saveFamilyDTO){

        if (family == null || saveFamilyDTO == null) return;

        family.setName(saveFamilyDTO.getName());
    }

    public static CategoryDTO.FamilyDTO toGetFamilyDto(Family family){
        if (family == null ) return null;

        return new CategoryDTO.FamilyDTO(
                family.getId(),
                family.getName()
        );

    }

}
