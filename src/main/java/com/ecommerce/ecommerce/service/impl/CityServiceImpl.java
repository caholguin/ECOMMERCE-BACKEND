package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.search.CitySearchDTO;
import com.ecommerce.ecommerce.dto.response.CityDTO;
import com.ecommerce.ecommerce.entity.Address;
import com.ecommerce.ecommerce.entity.City;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.CityMapper;
import com.ecommerce.ecommerce.repository.AddressRepository;
import com.ecommerce.ecommerce.repository.CityRepository;
import com.ecommerce.ecommerce.repository.epecification.CitySearch;
import com.ecommerce.ecommerce.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;

    public CityServiceImpl(CityRepository cityRepository, AddressRepository addressRepository){
        this.cityRepository = cityRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Page<CityDTO> findAll(CitySearchDTO search, Pageable pageable){
        CitySearch citySearch = new CitySearch(search);

        Page<City> cities = cityRepository.findAll(citySearch,pageable);
        return cities.map(CityMapper::toDto);
    }

    @Override
    public CityDTO findById(Long id){
        return CityMapper.toDto(this.findByIdEntity(id));
    }

    @Override
    public CityDTO findByAddressId(Long id){
        Address address = this.addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Dirección con id " + id + " no encontrada"));

        return CityMapper.toDto(this.findByIdEntity(address.getCity().getId()));
    }

    @Override
    public City findByIdEntity(Long id){
        return cityRepository.findById(id) .orElseThrow(() -> new ObjectNotFoundException("Ciudad con ID: " + id + " no encontrada"));
    }
}
