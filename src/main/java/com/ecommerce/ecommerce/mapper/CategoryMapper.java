package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.dto.FamilyDTO;
import com.ecommerce.ecommerce.dto.SubcategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Family;
import com.ecommerce.ecommerce.entity.SubCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category){

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setFamilyId(category.getFamilyId());

        if (category.getFamily() != null){
            Family family = category.getFamily();
            FamilyDTO familyDTO = new FamilyDTO();
            familyDTO.setId(family.getId());
            familyDTO.setName(family.getName());
            categoryDTO.setFamily(familyDTO);
        }

        if (category.getSubCategories() != null) {
            List<SubcategoryDTO> subcategoryDTOs = new ArrayList<>();
            for (SubCategory subcategory : category.getSubCategories()) {
                SubcategoryDTO subcategoryDTO = new SubcategoryDTO();
                subcategoryDTO.setId(subcategory.getId());
                subcategoryDTO.setName(subcategory.getName());
                subcategoryDTOs.add(subcategoryDTO);
            }
            categoryDTO.setSubCategories(subcategoryDTOs);
        }

        return categoryDTO;
    }

    public Category toEntity(CategoryDTO categoryDTO){

        Category category = new Category();

        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setFamilyId(categoryDTO.getFamily().getId());

        if (categoryDTO.getFamily() != null){
            FamilyDTO familyDTO = new FamilyDTO();
            Family family = new Family();
            family.setId(familyDTO.getId());
            family.setName(familyDTO.getName());
            category.setFamily(family);
        }


        return category;
    }

    public CategoryDTO toSimpleDTO(Category category){

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }
}
