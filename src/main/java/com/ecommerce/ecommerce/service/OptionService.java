package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.OptionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OptionService {

    Page<OptionDTO> findAll(Pageable pageable);

    OptionDTO save(OptionDTO optionDTO);

    Optional<OptionDTO> findById(Long id);

    OptionDTO update(Long id, OptionDTO optionDTO);

    OptionDTO delete(Long id);
}
