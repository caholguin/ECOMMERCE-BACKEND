package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.request.SaveSubcategoryDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.SubCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubCategoryMapper {

    public SubcategoryDTO toDto(SubCategory subCategory){

        if(subCategory == null) return null;

        SubcategoryDTO subcategoryDTO = new SubcategoryDTO();
        subcategoryDTO.setId(subCategory.getId());
        subcategoryDTO.setName(subCategory.getName());
        subcategoryDTO.setCategory(CategoryMapper.toSubCategoryDto(subCategory.getCategory()));

        return subcategoryDTO;
    }

    public SubcategoryDTO toDetailDto(SubCategory subCategory){
        if(subCategory == null) return null;

        SubcategoryDTO subcategoryDTO = new SubcategoryDTO();
        subcategoryDTO.setId(subCategory.getId());
        subcategoryDTO.setName(subCategory.getName());
        subcategoryDTO.setCategory(CategoryMapper.toSubCategoryDto(subCategory.getCategory()));
        subcategoryDTO.setProducts(ProductMapper.toProductsSubCategoryDTO(subCategory.getProducts()));

        return subcategoryDTO;
    }


    public SubCategory toEntity(SaveSubcategoryDTO saveSubcategoryDTO, Category category){

        if(saveSubcategoryDTO == null) return null;

        SubCategory subCategory = new SubCategory();
        subCategory.setName(saveSubcategoryDTO.getName());
        subCategory.setCategory(category);

        return subCategory;
    }

    public static void updateEntity(SubCategory subCategory,SaveSubcategoryDTO saveSubcategoryDTO,Category category){
        if(subCategory == null || saveSubcategoryDTO == null) return;

        subCategory.setName(saveSubcategoryDTO.getName());
        subCategory.setCategory(category);

    }


    public static CategoryDTO.SubcategoryDTO toSubCategoriesCategoryDto(SubCategory subCategory){
        if (subCategory == null ) return null;

        return new CategoryDTO.SubcategoryDTO(
                subCategory.getId(),
                subCategory.getName()
        );
    }


    public static List<CategoryDTO.SubcategoryDTO> toSubCategoriesCategoriesDto(List<SubCategory> subCategories){
        if (subCategories == null ) return null;

        return subCategories.stream()
                .map(SubCategoryMapper::toSubCategoriesCategoryDto)
                .toList();
    }
}
