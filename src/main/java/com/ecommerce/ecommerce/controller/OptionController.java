package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.OptionDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.service.OptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping
    public ResponseEntity<Page<OptionDTO>> findAll(Pageable pageable){
        Page<OptionDTO> options = optionService.findAll(pageable);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OptionDTO> save(@RequestBody @Valid OptionDTO optionDTO){
        OptionDTO option = optionService.save(optionDTO);
        return new ResponseEntity<>(option,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionDTO> findById(@PathVariable Long id){
        OptionDTO option = optionService.findById(id).orElseThrow(()-> new ObjectNotFoundException("No existe una opción con el id: " + id));
        return new ResponseEntity<>(option,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionDTO> update(@PathVariable Long id, @RequestBody @Valid OptionDTO optionDTO){
        OptionDTO option = optionService.update(id,optionDTO);
        return new ResponseEntity<>(option,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OptionDTO> delete(@PathVariable Long id){
        OptionDTO option = optionService.delete(id);
        return new ResponseEntity<>(option,HttpStatus.OK);
    }

}
