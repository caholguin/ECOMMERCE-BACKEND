package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.response.AddressDTO;
import com.ecommerce.ecommerce.dto.response.CategoryDTO;
import com.ecommerce.ecommerce.entity.City;
import com.ecommerce.ecommerce.entity.Family;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public static AddressDTO.CityDTO toGetCityDto(City city){
        if (city == null ) return null;

        return new AddressDTO.CityDTO(
                city.getId(),
                city.getName(),
                city.getPrice()
        );

    }
}
