package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.dto.request.FeatureSearchDTO;
import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.FeatureMapper;
import com.ecommerce.ecommerce.repository.FeatureRepository;
import com.ecommerce.ecommerce.repository.epecification.FeatureSearch;
import com.ecommerce.ecommerce.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureMapper featureMapper;

    @Override
    public Page<FeatureDTO> findAll(FeatureSearchDTO search, Pageable pageable){
        FeatureSearch featureSearch = new FeatureSearch(search);

        Page<Feature> featuresPage = featureRepository.findAll(featureSearch,pageable);
        return featuresPage.map(FeatureMapper::toDTO);
    }

    @Override
    public FeatureDTO save(FeatureDTO featureDTO){

        Feature feature = new Feature();




        Feature featureSaved = featureRepository.save(feature);

        return FeatureMapper.toDTO(featureSaved);
    }

    @Override
    public Optional<FeatureDTO> findById(Long id){
        Optional<Feature> feautureOptional = featureRepository.findById(id);

        if(feautureOptional.isEmpty()){
            throw new ObjectNotFoundException("No existe una característica con el id " + id);
        }

        return feautureOptional.map(FeatureMapper::toDTO);
    }

    @Override
    public FeatureDTO update(Long id, FeatureDTO featureDTO){
        Optional<Feature> feautureOptional = featureRepository.findById(id);

        if(feautureOptional.isEmpty()){
            throw new ObjectNotFoundException("No existe una característica con el id " + id);
        }

        Feature feature = feautureOptional.get();



        Feature featureUpdated = featureRepository.save(feature);

        return FeatureMapper.toDTO(featureUpdated);
    }

    @Override
    public FeatureDTO delete(Long id){

        Optional<FeatureDTO> feature = this.findById(id);

        featureRepository.deleteById(id);

        return feature.get();
    }

    @Override
    public Feature findByIdEntity(Long id){
        return featureRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("feature con ID: " + id + " no encontrada"));

    }


}
