package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.SubcategoryDTO;
import com.ecommerce.ecommerce.entity.SubCategory;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.SubCategoryMapper;
import com.ecommerce.ecommerce.repository.SubCategoryRepository;
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

    @Override
    public Page<SubcategoryDTO> findAll(Pageable pageable){
        Page<SubCategory> subCategories = subCategoryRepository.findAll(pageable);

        return subCategories.map(subCategoryMapper::toDTO);
    }

    @Override
    public SubcategoryDTO save(SubcategoryDTO subcategoryDTO){

        SubCategory subCategory = new SubCategory();

        subCategory.setName(subcategoryDTO.getName());


        SubCategory saveSubCategory = subCategoryRepository.save(subCategory);

        return subCategoryMapper.toDTO(saveSubCategory);
    }

    @Override
    public Optional<SubcategoryDTO> findById(Long id) throws ObjectNotFoundException{
        Optional<SubCategory> subCategory = subCategoryRepository.findById(id);

        if (subCategory.isEmpty()){
            throw new ObjectNotFoundException("No existe una subcategoría con el id " + id);
        }

        return subCategory.map(subCategoryMapper::toDTO);
    }

    @Override
    public SubcategoryDTO update(Long id, SubcategoryDTO subcategoryDTO){

        Optional<SubCategory> subCategoryOptional = subCategoryRepository.findById(id);

        if (subCategoryOptional.isEmpty()){
            throw new ObjectNotFoundException("No existe una subcategoría con el id " + id);
        }

        SubCategory subCategory = subCategoryOptional.get();
        subCategory.setName(subcategoryDTO.getName());


        SubCategory subCategoryUpdate = subCategoryRepository.save(subCategory);

        return subCategoryMapper.toDTO(subCategoryUpdate);
    }

    @Override
    public SubcategoryDTO delete(Long id){
        Optional<SubCategory> subCategoryOptional = subCategoryRepository.findById(id);

        if (subCategoryOptional.isEmpty()){
            throw new ObjectNotFoundException("No existe una subcategoría con el id " + id);
        }

        SubCategory subCategory = subCategoryOptional.get();
        subCategoryRepository.delete(subCategory);

        return subCategoryMapper.toDTO(subCategory);
    }
}
