package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveAddressDTO;
import com.ecommerce.ecommerce.dto.response.AddressDTO;
import com.ecommerce.ecommerce.entity.Address;

import java.util.List;

public interface AddressService {

    AddressDTO create(SaveAddressDTO saveAddressDTO);

    AddressDTO findById(Long id);

    AddressDTO update(Long id, SaveAddressDTO saveAddressDTO);

    Address findByIdEntity(Long id);

   AddressDTO findByUser(Long userId);
}
