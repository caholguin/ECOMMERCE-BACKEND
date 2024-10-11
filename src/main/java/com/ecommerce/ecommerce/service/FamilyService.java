package com.ecommerce.ecommerce.service;
import com.ecommerce.ecommerce.dto.request.FamilySearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveFamilyDTO;
import com.ecommerce.ecommerce.dto.response.FamilyDTO;
import com.ecommerce.ecommerce.entity.Family;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FamilyService {

    Page<FamilyDTO> findAll(FamilySearchDTO search,Pageable pageable);

    FamilyDTO save(SaveFamilyDTO saveFamilyDTO);

    FamilyDTO findById(Long id);

    FamilyDTO update(Long id, SaveFamilyDTO saveFamilyDTO);

    void delete(Long id);

    Family findByIdEntity(Long id);
}
