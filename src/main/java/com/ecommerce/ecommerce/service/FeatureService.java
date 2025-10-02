package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveFeatureDTO;
import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.dto.request.search.FeatureSearchDTO;
import com.ecommerce.ecommerce.entity.Feature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeatureService {

    Page<FeatureDTO> findAll(FeatureSearchDTO featureSearchDTO, Pageable pageable);

    FeatureDTO save(SaveFeatureDTO saveFeatureDTO);

    FeatureDTO findById(Long id);

    FeatureDTO update(Long id, SaveFeatureDTO saveFeatureDTO);

    void delete(Long id);

    Feature findByIdEntity(Long id);

}
