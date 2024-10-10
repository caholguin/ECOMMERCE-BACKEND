package com.ecommerce.ecommerce.service;


import com.ecommerce.ecommerce.dto.request.FamilySearchDTO;
import com.ecommerce.ecommerce.dto.response.FamilyDTO;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FamilyService {

    Page<FamilyDTO> findAll(FamilySearchDTO search,Pageable pageable);

    FamilyDTO save(FamilyDTO familyDTO);

    Optional<FamilyDTO> findById(Long id) throws ObjectNotFoundException;

    //FamilyDTO findById(Long id) throws ObjectNotFoundException;

    FamilyDTO update(Long id, FamilyDTO familyDTO);

    FamilyDTO delete(Long id);

}
