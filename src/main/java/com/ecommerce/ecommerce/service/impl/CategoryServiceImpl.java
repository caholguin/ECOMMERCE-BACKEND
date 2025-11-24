package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.search.CategorySearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Family;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.CategoryMapper;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import com.ecommerce.ecommerce.repository.epecification.CategorySearch;
import com.ecommerce.ecommerce.service.CategoryService;
import com.ecommerce.ecommerce.service.FamilyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FamilyService familyService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, FamilyService familyService){
        this.categoryRepository = categoryRepository;
        this.familyService = familyService;
    }

    @Override
    public Page<CategoryDTO> findAll(CategorySearchDTO search,Pageable pageable){

        CategorySearch categorySearch = new CategorySearch(search);

        Page<Category> categories = categoryRepository.findAll(categorySearch,pageable);
        return categories.map(CategoryMapper::toDto);
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

        Family family = familyService.findByIdEntity(saveCategoryDTO.getFamilyId());

        Category category = this.findByIdEntity(id);
        CategoryMapper.updateEntity(category,saveCategoryDTO,family);

        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id){
        Category category = this.findByIdEntity(id);
        categoryRepository.delete(category);
    }

    public Category findByIdEntity(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Categoría con ID: " + id + " no encontrada"));

    }

    @Override
    public List<CategoryDTO> findTopCategoriesBySales(){
        return CategoryMapper.toDtoList(categoryRepository.findTopCategoriesBySales());
    }

    @Override
    public CategoryDTO findBySlug(String slug){
        return CategoryMapper.toDto(categoryRepository.findBySlug(slug).orElseThrow(() -> new ObjectNotFoundException("Categoría con Slug: " + slug + " no encontrada")));
    }

}
