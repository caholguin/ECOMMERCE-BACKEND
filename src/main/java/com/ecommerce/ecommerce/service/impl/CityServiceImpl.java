package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.response.CityDTO;
import com.ecommerce.ecommerce.entity.City;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.CityMapper;
import com.ecommerce.ecommerce.repository.CityRepository;
import com.ecommerce.ecommerce.service.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDTO findById(Long id){
        return CityMapper.toDto(this.findByEntity(id));
    }

    @Override
    public City findByEntity(Long id){
        return cityRepository.findById(id) .orElseThrow(() -> new ObjectNotFoundException("Ciudad con ID: " + id + " no encontrada"));
    }
}
