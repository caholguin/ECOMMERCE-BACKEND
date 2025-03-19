package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.SaveProductDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.request.ProductSearchDTO;
import com.ecommerce.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

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
        ProductDTO product = productService.findById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody @Valid SaveProductDTO saveProductDTO) {
        ProductDTO product = productService.update(id,saveProductDTO);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/combinations/{productId}")
    public ResponseEntity<Void> triggerVariants(@PathVariable Long productId) {
        productService.triggerVariants(productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/subcategory/{subcategoryId}/status/{status}")
    public ResponseEntity<List<ProductDTO>> findBySubCategoryId(@PathVariable Long subcategoryId, @PathVariable Integer status) {
        List<ProductDTO> products = productService.findBySubCategoryId(subcategoryId,status);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
