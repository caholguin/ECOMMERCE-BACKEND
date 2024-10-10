package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.FeatureDTO;
import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.FeatureMapper;
import com.ecommerce.ecommerce.repository.FeatureRepository;
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
    public Page<FeatureDTO> findAll(Pageable pageable){
        Page<Feature> features = featureRepository.findAll(pageable);
        return features.map(featureMapper::toDTO);
    }

    @Override
    public FeatureDTO save(FeatureDTO featureDTO){

        Feature feature = new Feature();

        feature.setValue(featureDTO.getValue());
        feature.setDescription(featureDTO.getDescription());


        Feature featureSaved = featureRepository.save(feature);

        return featureMapper.toDTO(featureSaved);
    }

    @Override
    public Optional<FeatureDTO> findById(Long id){
        Optional<Feature> feautureOptional = featureRepository.findById(id);

        if(feautureOptional.isEmpty()){
            throw new ObjectNotFoundException("No existe una característica con el id " + id);
        }

        return feautureOptional.map(featureMapper::toDTO);
    }

    @Override
    public FeatureDTO update(Long id, FeatureDTO featureDTO){
        Optional<Feature> feautureOptional = featureRepository.findById(id);

        if(feautureOptional.isEmpty()){
            throw new ObjectNotFoundException("No existe una característica con el id " + id);
        }

        Feature feature = feautureOptional.get();
        feature.setValue(featureDTO.getValue());
        feature.setDescription(featureDTO.getDescription());


        Feature featureUpdated = featureRepository.save(feature);

        return featureMapper.toDTO(featureUpdated);
    }

    @Override
    public FeatureDTO delete(Long id){

        Optional<FeatureDTO> feature = this.findById(id);

        featureRepository.deleteById(id);

        return feature.get();
    }
}
