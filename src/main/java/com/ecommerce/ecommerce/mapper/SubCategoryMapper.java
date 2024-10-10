package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.dto.SubcategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.SubCategory;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryMapper {

    public SubcategoryDTO toDTO(SubCategory subCategory){

        SubcategoryDTO subcategoryDTO = new SubcategoryDTO();

        subcategoryDTO.setId(subCategory.getId());
        subcategoryDTO.setName(subCategory.getName());


        if (subCategory.getCategory() != null){
            Category category = subCategory.getCategory();
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());

            subcategoryDTO.setCategory(categoryDTO);
        }
        return subcategoryDTO;
    }
}
