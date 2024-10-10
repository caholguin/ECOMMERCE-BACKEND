package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.CategoryMapper;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import com.ecommerce.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl  {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

   /* @Override
    public Page<CategoryDTO> findAll(Pageable pageable){

        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toDTO);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO){

       Category category = new Category();

       category.setName(categoryDTO.getName());


        Category categorySave = categoryRepository.save(category);

        return categoryMapper.toDTO(categorySave);
    }

    @Override
    public Optional<CategoryDTO> findById(Long id) throws ObjectNotFoundException{
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()){
            throw new ObjectNotFoundException("No existe una categoría con el id: " + id);
        }

        return category.map(categoryMapper::toDTO);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO){
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new ObjectNotFoundException("No existe una categoría con el id: " + id);
        }

        Category category = optionalCategory.get();

        category.setName(categoryDTO.getName());


        Category categoryUpdate = categoryRepository.save(category);

        return categoryMapper.toDTO(categoryUpdate);
    }

    @Override
    public CategoryDTO delete(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new ObjectNotFoundException("No existe una familia con id: " + id);
        }

        Category category = optionalCategory.get();
        categoryRepository.delete(category);

        return categoryMapper.toDTO(category);
    }*/
}
