package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.search.OptionSearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveOptionDTO;
import com.ecommerce.ecommerce.dto.response.OptionDTO;
import com.ecommerce.ecommerce.service.OptionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService){
        this.optionService = optionService;
    }

    @GetMapping
    public ResponseEntity<Page<OptionDTO>> findAll(Pageable pageable, @RequestParam(required = false) String name){

        OptionSearchDTO optionSearchDTO = new OptionSearchDTO(name);

        Page<OptionDTO> options = optionService.findAll(optionSearchDTO,pageable);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OptionDTO> create(@RequestBody @Valid SaveOptionDTO saveOptionDTO){
        OptionDTO option = optionService.save(saveOptionDTO);
        return new ResponseEntity<>(option,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionDTO> findById(@PathVariable Long id){
        OptionDTO option = optionService.findById(id);
        return new ResponseEntity<>(option,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionDTO> update(@PathVariable Long id, @RequestBody @Valid SaveOptionDTO saveOptionDTO){
        OptionDTO option = optionService.update(id,saveOptionDTO);
        return new ResponseEntity<>(option,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        optionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/subcategory/{subcategoryId}")
    public ResponseEntity<List<OptionDTO>> findBySubcategory(@PathVariable Long subcategoryId){
        List<OptionDTO> options = optionService.findBySubcategory(subcategoryId);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

}
