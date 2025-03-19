package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.service.ImageVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imagevariant")
public class ImageVariantController {

    @Autowired
    private ImageVariantService imageVariantService;

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imageVariantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
