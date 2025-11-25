package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.search.CategorySearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    Page<CategoryDTO> findAll(CategorySearchDTO categorySearchDTO, Pageable pageable);

    CategoryDTO save(SaveCategoryDTO saveCategoryDTO);

    CategoryDTO findById(Long id);

    CategoryDTO update(Long id, SaveCategoryDTO saveCategoryDTO);

    void delete(Long id);

    Category findByIdEntity(Long id);

    List<CategoryDTO> findTopCategoriesBySales();

    CategoryDTO findBySlug(String slug);
}
