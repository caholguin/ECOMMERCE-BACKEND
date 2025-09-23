package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.response.AddressDTO;
import com.ecommerce.ecommerce.dto.response.CityDTO;
import com.ecommerce.ecommerce.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public static CityDTO toDto(City city){
        if (city == null) return null;

        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setPrice(city.getPrice());

        return cityDTO;
    }

    public static AddressDTO.CityDTO toGetCityDto(City city){
        if (city == null ) return null;

        return new AddressDTO.CityDTO(
                city.getId(),
                city.getName(),
                city.getPrice()
        );

    }
}
