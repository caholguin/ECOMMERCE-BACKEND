package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.FamilyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FamilyService {

    Page<FamilyDTO> findAll(Pageable pageable);

    Optional<FamilyDTO> findById(Long id);

    FamilyDTO save(FamilyDTO familyDTO);

    FamilyDTO update(Long id, FamilyDTO familyDTO);

    void delete(FamilyDTO familyDTO);


}
