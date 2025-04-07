package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.response.CityDTO;
import com.ecommerce.ecommerce.entity.City;

public interface CityService {

    CityDTO findById(Long id);

    City findByEntity(Long id);
}
