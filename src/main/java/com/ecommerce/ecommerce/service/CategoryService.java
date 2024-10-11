package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<CategoryDTO> findAll(Pageable pageable);

    CategoryDTO save(SaveCategoryDTO saveCategoryDTO);

    CategoryDTO findById(Long id);

    CategoryDTO update(Long id, SaveCategoryDTO saveCategoryDTO);

    CategoryDTO delete(Long id);

}
