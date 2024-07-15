package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.SubcategoryDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.service.SubCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class SubCategoryServiceImpl implements SubCategoryService {
    @Override
    public Page<SubcategoryDTO> findAll(Pageable pageable){
        return null;
    }

    @Override
    public SubcategoryDTO save(SubcategoryDTO subcategoryDTO){
        return null;
    }

    @Override
    public Optional<SubcategoryDTO> findById(Long id) throws ObjectNotFoundException{
        return Optional.empty();
    }

    @Override
    public SubcategoryDTO update(Long id, SubcategoryDTO subcategoryDTO){
        return null;
    }

    @Override
    public SubcategoryDTO delete(Long id){
        return null;
    }
}
