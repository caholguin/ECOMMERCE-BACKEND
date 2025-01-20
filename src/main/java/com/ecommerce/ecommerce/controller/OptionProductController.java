package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.SaveOptionProductDTO;
import com.ecommerce.ecommerce.dto.response.OptionProductDTO;
import com.ecommerce.ecommerce.service.OptionProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/option-product")
@CrossOrigin("*")
public class OptionProductController {

    private final OptionProductService optionProductService;

    public OptionProductController(OptionProductService optionProductService){
        this.optionProductService = optionProductService;
    }

    @PostMapping
    public ResponseEntity<OptionProductDTO> create(@RequestBody SaveOptionProductDTO saveOptionProductDTO) {
        OptionProductDTO optionProduct = optionProductService.save(saveOptionProductDTO);
        return new ResponseEntity<>(optionProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/{featureId}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long featureId) {
        optionProductService.delete(id,featureId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
