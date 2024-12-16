package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.dto.request.FeatureSearchDTO;
import com.ecommerce.ecommerce.entity.Feature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface FeatureService {

    Page<FeatureDTO> findAll(FeatureSearchDTO featureSearchDTO, Pageable pageable);

    FeatureDTO save(FeatureDTO featureDTO);

    Optional<FeatureDTO> findById(Long id);

    FeatureDTO update(Long id, FeatureDTO featureDTO);

    FeatureDTO delete(Long id);

    Feature findByIdEntity(Long id);

}
