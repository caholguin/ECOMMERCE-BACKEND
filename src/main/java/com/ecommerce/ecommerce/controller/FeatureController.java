package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.FeatureDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.service.FeatureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @GetMapping()
    public ResponseEntity<Page<FeatureDTO>> findAll(Pageable pageable){
        Page<FeatureDTO> features = featureService.findAll(pageable);
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<FeatureDTO> save(@RequestBody @Valid FeatureDTO featureDTO){
        FeatureDTO feature = featureService.save(featureDTO);
        return new ResponseEntity<>(feature, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureDTO> findById(@PathVariable Long id){
        FeatureDTO feature = featureService.findById(id).orElseThrow(() -> new ObjectNotFoundException("No existe una característica con el id " + id));
        return new ResponseEntity<>(feature,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureDTO> update(@PathVariable Long id, @RequestBody @Valid FeatureDTO featureDTO){
        FeatureDTO feature = featureService.update(id,featureDTO);
        return new ResponseEntity<>(feature,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FeatureDTO> delete(@PathVariable Long id){
        FeatureDTO feature = featureService.delete(id);
        return new ResponseEntity<>(feature,HttpStatus.OK);
    }


}
