package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveOptionProductDTO;
import com.ecommerce.ecommerce.dto.request.SaveProductDTO;
import com.ecommerce.ecommerce.dto.response.OptionProductDTO;
import com.ecommerce.ecommerce.entity.Option;
import com.ecommerce.ecommerce.entity.OptionProduct;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.mapper.OptionProductMapper;
import com.ecommerce.ecommerce.repository.OptionProductRespository;
import com.ecommerce.ecommerce.service.OptionProductService;
import com.ecommerce.ecommerce.service.OptionService;
import com.ecommerce.ecommerce.service.ProductService;
import com.ecommerce.ecommerce.service.VariantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OptionProductServiceImpl implements OptionProductService {

    private final OptionProductRespository optionProductRespository;
    private final OptionService optionService;
    private final ProductService productService;
    private final VariantService variantService;

    public OptionProductServiceImpl(OptionProductRespository optionProductRespository, OptionService optionService, ProductService productService, VariantService variantService){

        this.optionProductRespository = optionProductRespository;
        this.optionService = optionService;
        this.productService = productService;
        this.variantService = variantService;
    }

    @Override
    public OptionProductDTO save(SaveOptionProductDTO saveOptionProductDTO){

        Option option = optionService.findByIdEntity(saveOptionProductDTO.getOptionId());
        Product product = productService.findByIdEntity(saveOptionProductDTO.getProductId());

        OptionProduct optionProduct = OptionProductMapper.toEntity(saveOptionProductDTO,option,product);

        OptionProduct savedOptionProduct = optionProductRespository.save(optionProduct);

        productService.triggerVariants(saveOptionProductDTO.getProductId());

        return OptionProductMapper.toDto(optionProductRespository.save(savedOptionProduct));
    }

    @Override
    public void delete(Long id, Long featureId) {
        List<OptionProduct> items = optionProductRespository.findByProductId(id);

        for (OptionProduct item : items) {
            List<Map<String, String>> features = item.getFeatures();

            features.removeIf(feature -> feature.containsKey("id") &&
                    Long.parseLong(feature.get("id")) == featureId);

            optionProductRespository.save(item);

            if (features.isEmpty()) {
                optionProductRespository.delete(item);
                variantService.deleteByProductId(id);
            }
        }

        if (items.isEmpty()){
                variantService.deleteByProductId(id);
        }
        productService.triggerVariants(id);
    }

    @Override
    public OptionProductDTO update(SaveOptionProductDTO saveOptionProductDTO){

        OptionProduct optionProduct = optionProductRespository.findByProductIdAndOptionId(saveOptionProductDTO.getProductId(), saveOptionProductDTO.getOptionId());

        List<Map<String, String>> existingFeatures = optionProduct.getFeatures();
        if (existingFeatures == null) {
            existingFeatures = new ArrayList<>();
        }

        for (Map<String, String> newFeature : saveOptionProductDTO.getFeatures()) {

            boolean exists = existingFeatures.stream()
                    .anyMatch(feature -> feature.get("id").equals(newFeature.get("id")));

            if (!exists) {
                existingFeatures.add(newFeature);
            }
        }

        optionProduct.setFeatures(existingFeatures);

        OptionProduct saved = optionProductRespository.save(optionProduct);

        productService.triggerVariants(saveOptionProductDTO.getProductId());

        return OptionProductMapper.toDto(saved);
    }
}
