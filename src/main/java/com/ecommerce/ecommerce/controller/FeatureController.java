package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.search.FeatureSearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveFeatureDTO;
import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.service.FeatureService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/features")
public class FeatureController {

    private final FeatureService featureService;

    public FeatureController(FeatureService featureService){
        this.featureService = featureService;
    }

    @GetMapping()
    public ResponseEntity<Page<FeatureDTO>> findAll(Pageable pageable, @RequestParam(required = false) String value, @RequestParam(required = false) Long option){
        FeatureSearchDTO featureSearchDTO = new FeatureSearchDTO(value,option);
        Page<FeatureDTO> features = featureService.findAll(featureSearchDTO,pageable);
        return new ResponseEntity<>(features, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<FeatureDTO> save(@RequestBody @Valid SaveFeatureDTO saveFeatureDTO){
        FeatureDTO feature = featureService.save(saveFeatureDTO);
        return new ResponseEntity<>(feature, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(featureService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureDTO> update(@PathVariable Long id, @RequestBody @Valid SaveFeatureDTO saveFeatureDTO){
        FeatureDTO feature = featureService.update(id,saveFeatureDTO);
        return new ResponseEntity<>(feature,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        featureService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
