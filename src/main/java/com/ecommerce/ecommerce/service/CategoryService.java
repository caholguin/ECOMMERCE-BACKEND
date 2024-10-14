package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.CategorySearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<CategoryDTO> findAll(CategorySearchDTO categorySearchDTO, Pageable pageable);

    CategoryDTO save(SaveCategoryDTO saveCategoryDTO);

    CategoryDTO findById(Long id);

    CategoryDTO update(Long id, SaveCategoryDTO saveCategoryDTO);

    void delete(Long id);

}
