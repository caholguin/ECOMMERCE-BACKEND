package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category){

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setFamily(category.getFamily());

        return categoryDTO;
    }

    public CategoryDTO toSimpleDTO(Category category){

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }

    public Category toEntity(CategoryDTO categoryDTO){

        Category category = new Category();

        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setFamily(categoryDTO.getFamily());

        return category;
    }
}
