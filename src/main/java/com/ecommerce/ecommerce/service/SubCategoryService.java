package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveSubcategoryDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.dto.request.SubcategorySearchDTO;
import com.ecommerce.ecommerce.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SubCategoryService {

    Page<SubcategoryDTO> findAll(SubcategorySearchDTO subcategorySearchDTO, Pageable pageable);

    SubcategoryDTO save(SaveSubcategoryDTO saveSubcategoryDTO);

    SubcategoryDTO findById(Long id);

    SubcategoryDTO update(Long id, SaveSubcategoryDTO saveSubcategoryDTO);

    void delete(Long id);

    SubCategory findByIdEntity(Long id);

    SubcategoryDTO findBySlug(String slug);
}
