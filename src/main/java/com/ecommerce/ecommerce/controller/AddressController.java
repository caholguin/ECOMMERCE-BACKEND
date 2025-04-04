package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.SaveAddressDTO;
import com.ecommerce.ecommerce.dto.response.AddressDTO;
import com.ecommerce.ecommerce.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @PostMapping()
    public ResponseEntity<AddressDTO> create(@RequestBody @Valid SaveAddressDTO saveAddressDTO){
        AddressDTO address = addressService.create(saveAddressDTO);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<AddressDTO> findByUser(@PathVariable Long userId){
        AddressDTO address = addressService.findByUser(userId);
        return new ResponseEntity<>(address,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long id, @RequestBody @Valid SaveAddressDTO saveAddressDTO){
        AddressDTO address = addressService.update(id,saveAddressDTO);
        return new ResponseEntity<>(address,HttpStatus.OK);
    }
}
