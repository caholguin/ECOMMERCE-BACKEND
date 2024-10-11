package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Family;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.CategoryMapper;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import com.ecommerce.ecommerce.service.CategoryService;
import com.ecommerce.ecommerce.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FamilyService familyService;

    @Override
    public Page<CategoryDTO> findAll(Pageable pageable){
        return null;
    }

    @Override
    public CategoryDTO save(SaveCategoryDTO saveCategoryDTO){

        Family family = familyService.findByIdEntity(saveCategoryDTO.getFamilyId());

        Category category = CategoryMapper.toEntity(saveCategoryDTO,family);

        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO findById(Long id){
        return CategoryMapper.toDto(this.findByIdEntity(id));
    }

    @Override
    public CategoryDTO update(Long id, SaveCategoryDTO saveCategoryDTO){
        return null;
    }

    @Override
    public CategoryDTO delete(Long id){
        return null;
    }

    private Category findByIdEntity(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoria con ID: " + id + " no encontrada"));
    }

}
