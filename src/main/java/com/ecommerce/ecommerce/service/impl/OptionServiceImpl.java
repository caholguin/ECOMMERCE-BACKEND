package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.OptionDTO;
import com.ecommerce.ecommerce.entity.Option;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.OptionMapper;
import com.ecommerce.ecommerce.repository.OptionRepository;
import com.ecommerce.ecommerce.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public Page<OptionDTO> findAll(Pageable pageable){
        Page<Option> options = optionRepository.findAll(pageable);
        return options.map(optionMapper::toDTO);
    }

    @Override
    public OptionDTO save(OptionDTO optionDTO){

        Option option = new Option();

        option.setName(optionDTO.getName());
        option.setType(optionDTO.getType());

        Option savedOption = optionRepository.save(option);

        return optionMapper.toDTO(savedOption);
    }

    @Override
    public Optional<OptionDTO> findById(Long id){

        Optional<Option> option = optionRepository.findById(id);

        if(option.isEmpty()){
            throw new ObjectNotFoundException("No existe una opción con el id: " + id);
        }

        return option.map(optionMapper::toDTO);
    }

    @Override
    public OptionDTO update(Long id, OptionDTO optionDTO){
        Optional<Option> optionOptional = optionRepository.findById(id);

        if(optionOptional.isEmpty()){
            throw new ObjectNotFoundException("No existe una opción con el id: " + id);
        }

        Option option = optionOptional.get();
        option.setName(optionDTO.getName());
        option.setType(optionDTO.getType());

        Option savedOption = optionRepository.save(option);

        return optionMapper.toDTO(savedOption);
    }

    @Override
    public OptionDTO delete(Long id){
        Optional<Option> optionOptional = optionRepository.findById(id);

        if(optionOptional.isEmpty()){
            throw new ObjectNotFoundException("No existe una opción con el id: " + id);
        }

        Option option = optionOptional.get();
        optionRepository.delete(option);

        return optionMapper.toDTO(option);
    }
}
