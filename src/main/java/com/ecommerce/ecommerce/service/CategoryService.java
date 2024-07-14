package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.dto.FamilyDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<CategoryDTO> findAll(Pageable pageable);

    CategoryDTO save(CategoryDTO categoryDTO);

    Optional<CategoryDTO> findById(Long id) throws ObjectNotFoundException;

    FamilyDTO update(Long id, CategoryDTO categoryDTO);

    FamilyDTO delete(Long id);

}
