package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.service.ImageVariantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image-variant")
public class ImageVariantController {

    private final ImageVariantService imageVariantService;

    public ImageVariantController(ImageVariantService imageVariantService){
        this.imageVariantService = imageVariantService;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imageVariantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/default/{id}")
    public ResponseEntity<Void> setDefault(@PathVariable Long id) {
        imageVariantService.setDefaultVariant(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
