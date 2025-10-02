package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.search.CitySearchDTO;
import com.ecommerce.ecommerce.dto.response.CityDTO;
import com.ecommerce.ecommerce.service.CityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<Page<CityDTO>> findAll(Pageable pageable, @RequestParam(required = false) String name){
        CitySearchDTO citySearchDTO = new CitySearchDTO(name);

        Page<CityDTO> cities = cityService.findAll(citySearchDTO,pageable);
        return new ResponseEntity<>(cities,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Long id){
        CityDTO city = cityService.findById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

   @GetMapping("address/{id}")
    public ResponseEntity<CityDTO> findByAddressId(@PathVariable Long id){
        CityDTO city = cityService.findByAddressId(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
