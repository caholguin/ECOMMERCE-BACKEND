package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveProductDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.request.ProductSearchDTO;
import com.ecommerce.ecommerce.entity.*;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.ProductMapper;
import com.ecommerce.ecommerce.repository.*;
import com.ecommerce.ecommerce.repository.epecification.ProductSearch;
import com.ecommerce.ecommerce.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private FeatureVariantRepository featureVariantRepository;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private VariantService variantService;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private OptionProductRespository optionProductRespository;

    @Override
    public Page<ProductDTO> findAll(ProductSearchDTO productSearchDTO, Pageable pageable){

        ProductSearch productSearch = new ProductSearch(productSearchDTO);

        Page<Product> products = productRepository.findAll(productSearch,pageable);
        return products.map(ProductMapper::toDto);
    }

    @Override
    public ProductDTO save(SaveProductDTO saveProductDTO){

        SubCategory subCategory = subCategoryService.findByIdEntity(saveProductDTO.getSubcategoryId());

        Product product = ProductMapper.toEntity(saveProductDTO,subCategory);
        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    public Optional<ProductDTO> findById(Long id){

        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ObjectNotFoundException("No existe un producto con el id: " + id);
        }

        return product.map(ProductMapper::toDto);
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
        product.setStatus(productDTO.getStatus());

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toDto(updatedProduct);
    }

    @Override
    public ProductDTO delete(Long id){
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ObjectNotFoundException("No existe un producto con el id: " + id);
        }

        Product product = productOptional.get();
        productRepository.delete(product);

        return ProductMapper.toDto(product);
    }

    @Override
    public Product findByIdEntity(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Producto con ID: " + id + " no encontrada"));

    }

    @Override
    public String addMedia(Long id, String url){
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ObjectNotFoundException("No existe un producto con el id: " + id);
        }

        Product product = productOptional.get();
        product.setImage(url);

       productRepository.save(product);

        return url;
    }

    @Override
    @Transactional
    public List<List<Long>> triggerVariants(Long productId){
        List<List<Long>> arrays = new ArrayList<>(List.of());

        //variantService.deleteByProductId(productId);

        List<OptionProduct> optionsProduct = optionProductRespository.findByProductId(productId);

        for (OptionProduct optionProduct : optionsProduct) {

            List<Map<String, String>> features = optionProduct.getFeatures();
            List<Long> featureIds = new ArrayList<>();

            for (Map<String, String> feature : features) {
                if (feature.containsKey("id")) {
                    featureIds.add(Long.parseLong(feature.get("id")));
                }
            }
            arrays.add(featureIds);

        }


        List<List<Long>> combinations = generateRecursiveCombinations(arrays, 0, new ArrayList<>());
        Product product = this.findByIdEntity(productId);

        List<List<Long>> newCombinations = new ArrayList<>();

        for (List<Long> combination : combinations) {

            System.out.println("combination = " + combination);

            if (!isCombinationExisting(combination, productId)) {
                newCombinations.add(combination);

                Variant variant = new Variant();
                variant.setProduct(product);
                variantRepository.save(variant);

                for (Long featureId : combination) {
                    Feature feature = featureService.findByIdEntity(featureId);
                    if (feature != null) {
                        FeatureVariant featureVariant = new FeatureVariant();
                        featureVariant.setVariant(variant);
                        featureVariant.setFeature(feature);
                        featureVariantRepository.save(featureVariant);
                    }
                }
            }
        }

        cleanUnusedVariants(productId, combinations);
        return newCombinations;
    }

    private boolean isCombinationExisting(List<Long> combination, Long productId){

        List<Variant> variants = variantService.findByProductoId(productId);

        for (Variant variant : variants) {
            boolean allFeaturesExist = true;

            for (Long item : combination) {
                if (!featureVariantRepository.existsByVariantIdAndFeatureId(variant.getId(), item)) {
                    allFeaturesExist = false;
                    break;
                }
            }

            if (allFeaturesExist) {
                return true;
            }
        }
        return false;
    }

    private List<List<Long>> generateRecursiveCombinations(List<List<Long>> arrays, int indice, List<Long> currentCombination) {
        if (indice == arrays.size()) {
            List<List<Long>> resultado = new ArrayList<>();
            resultado.add(new ArrayList<>(currentCombination));
            return resultado;
        }

        List<List<Long>> result = new ArrayList<>();

        for (Long item : arrays.get(indice)) {
            currentCombination.add(item);
            result.addAll(generateRecursiveCombinations(arrays, indice + 1, currentCombination));
            currentCombination.removeLast();
        }

        return result;
    }

    public void cleanUnusedVariants(Long productId, List<List<Long>> validCombinations) {
        List<Variant> variants = variantService.findByProductoId(productId);

        for (Variant variant : variants) {
            boolean isUsed = validCombinations.stream().anyMatch(combination ->
                    isCombinationMatching(variant, combination)
            );

            if (!isUsed) {
                featureVariantRepository.deleteById(variant.getId());
                variantRepository.delete(variant);
            }
        }
    }

    private boolean isCombinationMatching(Variant variant, List<Long> combination) {
        for (Long featureId : combination) {
            if (!featureVariantRepository.existsByVariantIdAndFeatureId(variant.getId(), featureId)) {
                return false;
            }
        }
        return true;
    }
}