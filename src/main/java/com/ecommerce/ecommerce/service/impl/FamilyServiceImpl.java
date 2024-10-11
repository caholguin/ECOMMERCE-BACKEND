package com.ecommerce.ecommerce.service.impl;
import com.ecommerce.ecommerce.dto.request.FamilySearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveFamilyDTO;
import com.ecommerce.ecommerce.dto.response.FamilyDTO;
import com.ecommerce.ecommerce.entity.Family;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.FamilyMapper;
import com.ecommerce.ecommerce.repository.FamilyRepository;
import com.ecommerce.ecommerce.repository.epecification.FamilySearch;
import com.ecommerce.ecommerce.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Page<FamilyDTO> findAll(FamilySearchDTO search,Pageable pageable){

        FamilySearch familySearch = new FamilySearch(search);

        Page<Family> familiesPage = familyRepository.findAll(familySearch,pageable);
        return familiesPage.map(FamilyMapper::toDto);
    }

    @Override
    public FamilyDTO save(SaveFamilyDTO saveFamilyDTO){

        Family family = FamilyMapper.toEntity(saveFamilyDTO);

        return FamilyMapper.toDto(familyRepository.save(family));
    }

     @Override
    public FamilyDTO findById(Long id) {
         return FamilyMapper.toDto(this.findByIdEntity(id));
    }

    @Override
    public FamilyDTO update(Long id, SaveFamilyDTO saveFamilyDTO){

        Family family = this.findByIdEntity(id);
        FamilyMapper.updateEntity(family, saveFamilyDTO);

        return FamilyMapper.toDto(familyRepository.save(family));
    }

    @Override
    public void delete(Long id){
        Family family = this.findByIdEntity(id);
        familyRepository.delete(family);
    }

    private Family findByIdEntity(Long id){
        return familyRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Familia con ID: " + id + " no encontrada"));
    }

}
