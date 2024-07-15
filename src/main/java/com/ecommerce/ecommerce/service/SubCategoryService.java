package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.SubcategoryDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SubCategoryService {

    Page<SubcategoryDTO> findAll(Pageable pageable);

    SubcategoryDTO save(SubcategoryDTO subcategoryDTO);

    Optional<SubcategoryDTO> findById(Long id) throws ObjectNotFoundException;

    SubcategoryDTO update(Long id, SubcategoryDTO subcategoryDTO);

    SubcategoryDTO delete(Long id);
}
