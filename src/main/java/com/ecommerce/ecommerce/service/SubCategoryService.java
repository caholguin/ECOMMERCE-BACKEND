package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveSubcategoryDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.dto.request.SubcategorySearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubCategoryService {

    Page<SubcategoryDTO> findAll(SubcategorySearchDTO subcategorySearchDTO, Pageable pageable);

    SubcategoryDTO save(SaveSubcategoryDTO saveSubcategoryDTO);

    SubcategoryDTO findById(Long id);

    SubcategoryDTO update(Long id, SaveSubcategoryDTO saveSubcategoryDTO);

    void delete(Long id);
}
