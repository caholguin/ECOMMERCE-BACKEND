package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.dto.response.FamilyDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Family;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public static CategoryDTO toDto(Category category){

        if(category == null) return null;

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setIcon(category.getIcon());
        categoryDTO.setFamily(FamilyMapper.toGetFamilyDto(category.getFamily()));
        categoryDTO.setSubCategories(SubCategoryMapper.toSubCategoriesCategoriesDto(category.getSubCategories()));

        return categoryDTO;
    }

    public static Category toEntity(SaveCategoryDTO saveCategoryDTO, Family family){

        if(saveCategoryDTO == null) return null;

        Category category = new Category();
        category.setName(saveCategoryDTO.getName());
        category.setIcon(saveCategoryDTO.getIcon());
        category.setFamily(family);

        return category;
    }

    public static void updateEntity(Category category, SaveCategoryDTO saveCategoryDTO,Family family){
        if(category == null || saveCategoryDTO == null) return;

        category.setName(saveCategoryDTO.getName());
        category.setIcon(saveCategoryDTO.getIcon());
        category.setFamily(family);
    }

    public static FamilyDTO.CategoryDTO toFamilyCategoryDto(Category category){
        if (category == null ) return null;

        return new FamilyDTO.CategoryDTO(
                category.getId(),
                category.getName(),
                category.getIcon()
        );
    }

    public static List<FamilyDTO.CategoryDTO> toFamilyCategoriesDTO(List<Category> categories){
        if (categories == null ) return null;

        return categories.stream()
                .map(CategoryMapper::toFamilyCategoryDto)
                .toList();
    }

    public static SubcategoryDTO.CategoryDTO toSubCategoryDto(Category category){
        if (category == null ) return null;

        return new SubcategoryDTO.CategoryDTO(
                category.getId(),
                category.getName(),
                category.getIcon()
        );
    }


}
