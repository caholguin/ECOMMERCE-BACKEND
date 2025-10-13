package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.request.SaveCityDTO;
import com.ecommerce.ecommerce.dto.response.AddressDTO;
import com.ecommerce.ecommerce.dto.response.CityDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.City;
import com.ecommerce.ecommerce.entity.Family;
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

    public static City toEntity(SaveCityDTO saveCityDTO){
        if (saveCityDTO == null) return null;

        City city = new City();
        city.setName(saveCityDTO.getName());
        city.setPrice(saveCityDTO.getPrice());

        return city;
    }

    public static void updateEntity(City city, SaveCityDTO saveCityDTO){
        if(city == null || saveCityDTO == null) return;

        city.setName(saveCityDTO.getName());
        city.setPrice(saveCityDTO.getPrice());
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
