package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.request.SaveAddressDTO;
import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.response.AddressDTO;
import com.ecommerce.ecommerce.dto.response.OptionDTO;
import com.ecommerce.ecommerce.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressMapper {

    public static AddressDTO toDto(Address address){
        if (address == null) return null;

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setDescription(address.getDescription());
        addressDTO.setDefault(address.isDefault());
        addressDTO.setFullName(address.getFullName());
        addressDTO.setNeighborhood(address.getNeighborhood());
        addressDTO.setPhone(address.getPhone());
        addressDTO.setCity(CityMapper.toGetCityDto(address.getCity()));
        addressDTO.setUserId(address.getUser().getId());

        return addressDTO;
    }

    public static Address toEntity (SaveAddressDTO saveAddressDTO,City city,User user){
        if (saveAddressDTO == null) return null;

        Address address = new Address();
        address.setDescription(saveAddressDTO.getDescription());
        address.setDefault(saveAddressDTO.isDefault());
        address.setFullName(saveAddressDTO.getFullName());
        address.setNeighborhood(saveAddressDTO.getNeighborhood());
        address.setPhone(saveAddressDTO.getPhone());
        address.setCity(city);
        address.setUser(user);

        return address;
    }

    public static void updateEntity(Address address, SaveAddressDTO saveAddressDTO, City city,User user){
        if(address == null || saveAddressDTO == null) return;

        address.setDescription(saveAddressDTO.getDescription());
        address.setDefault(saveAddressDTO.isDefault());
        address.setCity(city);
        address.setUser(user);
        address.setFullName(saveAddressDTO.getFullName());
        address.setNeighborhood(saveAddressDTO.getNeighborhood());
        address.setPhone(saveAddressDTO.getPhone());
    }

    public static List<AddressDTO> toDtoList(List<Address> addresses){

        if (addresses == null) return null;

        return addresses.stream()
                .map(AddressMapper::toDto)
                .toList();
    }
}
