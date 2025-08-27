package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveAddressDTO;
import com.ecommerce.ecommerce.dto.response.AddressDTO;
import com.ecommerce.ecommerce.entity.*;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.AddressMapper;
import com.ecommerce.ecommerce.repository.AddressRepository;
import com.ecommerce.ecommerce.service.AddressService;
import com.ecommerce.ecommerce.service.CityService;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final UserService userService;

    private final CityService cityService;

    public AddressServiceImpl(AddressRepository addressRepository, UserService userService, CityService cityService){
        this.addressRepository = addressRepository;
        this.userService = userService;
        this.cityService = cityService;
    }

    @Override
    @Transactional
    public AddressDTO create(SaveAddressDTO saveAddressDTO) {
        User user = userService.findByIdEntity(saveAddressDTO.getUserId());
        City city = cityService.findByEntity(saveAddressDTO.getCityId());

        addressRepository.unsetDefaultAddresses(user.getId());

        Address address = AddressMapper.toEntity(saveAddressDTO, city, user);
        address.setDefault(true);

        return AddressMapper.toDto(addressRepository.save(address));
    }


    @Override
    public AddressDTO findById(Long id){
        return AddressMapper.toDto(this.findByIdEntity(id));
    }

    @Override
    @Transactional
    public AddressDTO update(Long id, SaveAddressDTO saveAddressDTO){
        User user =  userService.findByIdEntity(saveAddressDTO.getUserId());
        City city = cityService.findByEntity(saveAddressDTO.getCityId());

        addressRepository.unsetDefaultAddresses(user.getId());

        Address address = this.findByIdEntity(id);
        AddressMapper.updateEntity(address,saveAddressDTO,city,user);

        address.setDefault(true);

        return AddressMapper.toDto(addressRepository.save(address));
    }

    @Override
    public Address findByIdEntity(Long id){
        return addressRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Dirección con ID: " + id + " no encontrada"));
    }

    @Override
    public List<AddressDTO> findByUser(Long userId){
        List<Address> addresses = addressRepository.findByUserId(userId);
        return AddressMapper.toDtoList(addresses);
    }

    @Override
    @Transactional
    public AddressDTO updateCurrentAddress(Long id){
        AddressDTO addressDTO = this.findById(id);
        Long userId = addressDTO.getUserId();

        List<Address> addresses = addressRepository.findByUserId(userId);

        for (Address a : addresses) {
            a.setDefault(false);
        }

        Address selected = addresses.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Dirección no encontrada"));
        selected.setDefault(true);

        addressRepository.saveAll(addresses);

        return AddressMapper.toDto(selected);
    }

}
