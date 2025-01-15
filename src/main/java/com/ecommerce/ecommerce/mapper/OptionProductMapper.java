package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.request.SaveOptionProductDTO;
import com.ecommerce.ecommerce.dto.response.OptionDTO;
import com.ecommerce.ecommerce.dto.response.OptionProductDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.entity.Option;
import com.ecommerce.ecommerce.entity.OptionProduct;
import com.ecommerce.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OptionProductMapper {

    public static OptionProductDTO toDto(OptionProduct optionProduct) {

        if (optionProduct == null) return null;

        OptionProductDTO optionProductDTO = new OptionProductDTO();
        optionProductDTO.setId(optionProduct.getId());
        optionProductDTO.setOption(OptionMapper.toGetOptionProductDTO(optionProduct.getOption()));
        optionProductDTO.setProduct(null);
        optionProductDTO.setFeatures(optionProduct.getFeatures());

        return optionProductDTO;
    }

    public static OptionProduct toEntity(SaveOptionProductDTO saveOptionProductDTO, Option option, Product product) {

        if (saveOptionProductDTO == null) return null;

        OptionProduct optionProduct = new OptionProduct();
        optionProduct.setId(saveOptionProductDTO.getId());
        optionProduct.setOption(option);
        optionProduct.setProduct(product);
        optionProduct.setFeatures(saveOptionProductDTO.getFeatures());

        return optionProduct;
    }


    public static ProductDTO.OptionProductDTO toOptionProductDTO(OptionProduct optionProduct){
        if (optionProduct == null) return null;

        OptionDTO optionDTO = OptionMapper.toDto(optionProduct.getOption());

        return new ProductDTO.OptionProductDTO(
                optionProduct.getId(),
                optionProduct.getFeatures(),
                optionDTO
        );
    }

    public static List<ProductDTO.OptionProductDTO> toOptionsProductDTO(List<OptionProduct> optionProduct){
        if (optionProduct == null) return null;

        return optionProduct.stream()
                .map(OptionProductMapper::toOptionProductDTO)
                .toList();
    }

}
