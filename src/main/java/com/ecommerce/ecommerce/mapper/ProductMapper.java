package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.dto.SubcategoryDTO;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.SubCategory;
import org.springframework.stereotype.Component;

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


        if (product.getSubCategory() != null){
            SubCategory subCategory = product.getSubCategory();
            SubcategoryDTO subcategoryDTO = new SubcategoryDTO();

            subcategoryDTO.setId(subCategory.getId());
            subcategoryDTO.setName(subCategory.getName());
            productDTO.setSubCategory(subcategoryDTO);
        }
        return productDTO;
    }
}
