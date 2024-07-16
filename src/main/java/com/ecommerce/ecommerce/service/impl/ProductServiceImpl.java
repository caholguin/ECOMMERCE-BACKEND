package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.ProductMapper;
import com.ecommerce.ecommerce.mapper.SubCategoryMapper;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SubCategoryMapper subCategoryMapper;

    @Override
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(productMapper::toDTO);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO){

        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDetail(productDTO.getDetail());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setSubCategoryId(productDTO.getSubCategoryId());

        Product savedProduct = productRepository.save(product);

        return productMapper.toDTO(savedProduct);
    }

    @Override
    public Optional<ProductDTO> findById(Long id){

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ObjectNotFoundException("No existe un producto con el id: " + id);
        }

        return product.map(productMapper::toDTO);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO){
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ObjectNotFoundException("No existe un producto con el id: " + id);
        }

        Product product = productOptional.get();
        product.setName(productDTO.getName());
        product.setDetail(productDTO.getDetail());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setSubCategoryId(productDTO.getSubCategoryId());

        Product updatedProduct = productRepository.save(product);

        return productMapper.toDTO(updatedProduct);
    }

    @Override
    public ProductDTO delete(Long id){
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ObjectNotFoundException("No existe un producto con el id: " + id);
        }

        Product product = productOptional.get();
        productRepository.delete(product);

        return productMapper.toDTO(product);
    }
}
