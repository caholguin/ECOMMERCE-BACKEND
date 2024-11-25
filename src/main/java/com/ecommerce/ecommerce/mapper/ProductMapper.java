package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.request.SaveProductDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.response.SubcategoryDTO;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.entity.SubCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public static ProductDTO toDto(Product product){

        if(product == null) return null;

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDetail(product.getDetail());
        productDTO.setImage(product.getImage());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setSubcategory(SubCategoryMapper.toGetSubCategoryDto(product.getSubCategory()));
        productDTO.setVariants(VariantMapper.toVariantsProductDto(product.getVariants()));

        return productDTO;
    }

    public static Product toEntity(SaveProductDTO saveProductDTO, SubCategory subCategory){
        if(saveProductDTO == null) return null;

        Product product = new Product();
        product.setId(saveProductDTO.getId());
        product.setName(saveProductDTO.getName());
        product.setDetail(saveProductDTO.getDetail());
        product.setImage(saveProductDTO.getImage());
        product.setPrice(saveProductDTO.getPrice());
        product.setStock(saveProductDTO.getStock());
        product.setStatus(saveProductDTO.getStatus());
        product.setSubCategory(subCategory);

        return product;
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
