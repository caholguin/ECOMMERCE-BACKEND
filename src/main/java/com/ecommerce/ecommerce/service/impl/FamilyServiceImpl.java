package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.FamilySearchDTO;
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

import java.util.Optional;

@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Page<FamilyDTO> findAll(FamilySearchDTO search,Pageable pageable){

        FamilySearch familySearch = new FamilySearch(search);

        Page<Family> familiesPage = familyRepository.findAll(familySearch,pageable);
        return familiesPage.map(FamilyMapper::toDTO);
    }

  /*  @Override
    public FamilyDTO findById(Long id) throws ObjectNotFoundException{

        Family family = familyRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("No existe una familia con el id: " + id));

        return familyMapper.toDTO(family);
    }*/

    @Override
    public FamilyDTO save(FamilyDTO familyDTO){

        return null;

       /* Family family = familyMapper.toEntity(familyDTO);

        Family familySave = familyRepository.save(family);

        return familyMapper.toDTO(familySave);*/
    }

    @Override
    public Optional<FamilyDTO> findById(Long id) throws ObjectNotFoundException{

        return null;


      /*  Optional<Family> family = familyRepository.findById(id);

        if (family.isEmpty()) {
            throw new ObjectNotFoundException("No existe una familia con id: " + id);
        }

        return family.map(familyMapper::toDTO);*/
    }

    @Override
    public FamilyDTO update(Long id, FamilyDTO familyDTO){

        return null;

       /* Optional<Family> optionalFamily = familyRepository.findById(id);

        if (optionalFamily.isEmpty()) {
            throw new ObjectNotFoundException("No existe una familia con id: " + id);
        }

        Family family = optionalFamily.get();
        family.setName(familyDTO.getName());

        Family updatedFamily = familyRepository.save(family);

        return familyMapper.toDTO(updatedFamily);*/
    }

    @Override
    public FamilyDTO delete(Long id){

        return null;
      /*  Optional<Family> optionalFamily = familyRepository.findById(id);

        if (optionalFamily.isEmpty()) {
            throw new ObjectNotFoundException("No existe una familia con id: " + id);
        }

        Family family = optionalFamily.get();
        familyRepository.delete(family);

        return familyMapper.toDTO(family);*/
    }
}
