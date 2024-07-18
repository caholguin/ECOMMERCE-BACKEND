package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.FeatureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FeatureService {

    Page<FeatureDTO> findAll(Pageable pageable);

    FeatureDTO save(FeatureDTO featureDTO);

    Optional<FeatureDTO> findById(Long id);

    FeatureDTO update(Long id, FeatureDTO featureDTO);

    FeatureDTO delete(Long id);
}
