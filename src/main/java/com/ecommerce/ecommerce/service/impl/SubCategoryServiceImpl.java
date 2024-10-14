package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveSubcategoryDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.dto.request.SubcategorySearchDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.SubCategory;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.CategoryMapper;
import com.ecommerce.ecommerce.mapper.SubCategoryMapper;
import com.ecommerce.ecommerce.repository.SubCategoryRepository;
import com.ecommerce.ecommerce.repository.epecification.SuCategorySearch;
import com.ecommerce.ecommerce.service.CategoryService;
import com.ecommerce.ecommerce.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SubCategoryMapper subCategoryMapper;

    @Autowired
    private CategoryService categoryService;


    @Override
    public Page<SubcategoryDTO> findAll(SubcategorySearchDTO subcategorySearchDTO, Pageable pageable){

        SuCategorySearch subCategorySearch = new SuCategorySearch(subcategorySearchDTO);
        Page<SubCategory> subCategories = subCategoryRepository.findAll(subCategorySearch,pageable);

        return subCategories.map(subCategoryMapper::toDto);
    }

    @Override
    public SubcategoryDTO save(SaveSubcategoryDTO saveSubcategoryDTO){

        Category category = categoryService.findByIdEntity(saveSubcategoryDTO.getCategoryId());

       SubCategory subCategory = subCategoryMapper.toEntity(saveSubcategoryDTO,category);

       return subCategoryMapper.toDto(subCategoryRepository.save(subCategory));
    }

    @Override
    public SubcategoryDTO findById(Long id){
        return subCategoryMapper.toDetailDto(this.findByIdEntity(id));
    }

    @Override
    public SubcategoryDTO update(Long id, SaveSubcategoryDTO saveSubcategoryDTO){

        Category category = categoryService.findByIdEntity(saveSubcategoryDTO.getCategoryId());

        SubCategory subCategory = this.findByIdEntity(id);
        SubCategoryMapper.updateEntity(subCategory,saveSubcategoryDTO,category);

        return subCategoryMapper.toDto(subCategoryRepository.save(subCategory));
    }

    @Override
    public void delete(Long id){
       SubCategory subCategory = this.findByIdEntity(id);
       subCategoryRepository.delete(subCategory);
    }

    public SubCategory findByIdEntity(Long id){
        return subCategoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("SubCategoria con ID: " + id + " no encontrada"));
    }
}
