package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveCityDTO;
import com.ecommerce.ecommerce.dto.request.search.CitySearchDTO;
import com.ecommerce.ecommerce.dto.response.CityDTO;
import com.ecommerce.ecommerce.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    Page<CityDTO> findAll(CitySearchDTO search, Pageable pageable);

    CityDTO create(SaveCityDTO saveCityDTO);

    CityDTO findById(Long id);

    CityDTO update(Long id, SaveCityDTO saveCityDTO);

    void delete(Long id);

    CityDTO findByAddressId(Long id);

    City findByIdEntity(Long id);
}
