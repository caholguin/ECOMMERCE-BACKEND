package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.SaveProductDTO;
import com.ecommerce.ecommerce.dto.request.TriggerVarinatsDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.request.ProductSearchDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable, @RequestParam(required = false) String name, @RequestParam(required = false) String detail, @RequestParam(required = false) Integer status) {

        ProductSearchDTO productSearchDTO = new ProductSearchDTO(name,detail,status);

        Page<ProductDTO> products = productService.findAll(productSearchDTO,pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid SaveProductDTO saveProductDTO) {
        ProductDTO product = productService.save(saveProductDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id).orElseThrow(() -> new ObjectNotFoundException("No existe un producto con el id: " + id));

        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        ProductDTO product = productService.update(id,productDTO);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
        ProductDTO product = productService.delete(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PostMapping("/combinaciones/{productId}")
    public ResponseEntity<Void> triggerVariants(@RequestBody TriggerVarinatsDTO triggerVarinatsDTO, @PathVariable Long productId) {
        productService.triggerVariants(triggerVarinatsDTO.getArrays(),productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
