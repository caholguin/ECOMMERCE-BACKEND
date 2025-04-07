package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.response.CityDTO;
import com.ecommerce.ecommerce.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable Long id){
        CityDTO city = cityService.findById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
