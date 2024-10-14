package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.SubCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product){

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDetail(product.getDetail());
        productDTO.setImage(product.getImage());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());

        return productDTO;
    }


    public static SubcategoryDTO.ProductDTO toProductSubCategoryDTO(Product product){

        if (product == null) return null;

        return new SubcategoryDTO.ProductDTO(
                product.getId(),
                product.getName(),
                product.getDetail(),
                product.getImage(),
                product.getPrice()
        );
    }

    public static List<SubcategoryDTO.ProductDTO> toProductsSubCategoryDTO(List<Product> products){
        if (products == null) return null;

        return products.stream()
                .map(ProductMapper::toProductSubCategoryDTO)
                .toList();
    }

}
