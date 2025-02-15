package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.OptionSearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveOptionDTO;
import com.ecommerce.ecommerce.dto.response.OptionDTO;
import com.ecommerce.ecommerce.entity.Option;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.OptionMapper;
import com.ecommerce.ecommerce.repository.OptionRepository;
import com.ecommerce.ecommerce.repository.epecification.OptionSearch;
import com.ecommerce.ecommerce.service.OptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;


    public OptionServiceImpl(OptionRepository optionRepository){
        this.optionRepository = optionRepository;
    }

    @Override
    public Page<OptionDTO> findAll(OptionSearchDTO search, Pageable pageable){

        OptionSearch optionSearch = new OptionSearch(search);

        Page<Option> options = optionRepository.findAll(optionSearch,pageable);
        return options.map(OptionMapper::toDto);
    }

    @Override
    public OptionDTO save(SaveOptionDTO saveOptionDTO){
        Option option = OptionMapper.toEntity(saveOptionDTO);
        return OptionMapper.toDto(optionRepository.save(option));
    }

    @Override
    public OptionDTO findById(Long id){
        return OptionMapper.toDto(this.findByIdEntity(id));
    }

    @Override
    public OptionDTO update(Long id, SaveOptionDTO saveOptionDTO){
        Option option = this.findByIdEntity(id);

        OptionMapper.updateEntity(option,saveOptionDTO);

        return OptionMapper.toDto(optionRepository.save(option));
    }

    @Override
    public void delete(Long id){
        Option option = this.findByIdEntity(id);
        optionRepository.delete(option);
    }

    public Option findByIdEntity(Long id){
        return optionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Opción con ID: " + id + " no encontrada"));

    }

    @Override
    public List<OptionDTO> findBySubcategory(Long subcategoryId){
        List<Option> options = optionRepository.findDistinctByOptionProductsProductSubCategoryId(subcategoryId);
        return OptionMapper.toDtoList(options);
    }
}
