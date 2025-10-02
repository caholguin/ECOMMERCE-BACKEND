package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveFeatureDTO;
import com.ecommerce.ecommerce.dto.response.FeatureDTO;
import com.ecommerce.ecommerce.dto.request.search.FeatureSearchDTO;
import com.ecommerce.ecommerce.entity.Feature;
import com.ecommerce.ecommerce.entity.Option;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.FeatureMapper;
import com.ecommerce.ecommerce.repository.FeatureRepository;
import com.ecommerce.ecommerce.repository.epecification.FeatureSearch;
import com.ecommerce.ecommerce.service.FeatureService;
import com.ecommerce.ecommerce.service.OptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;
    private final OptionService optionService;

    public FeatureServiceImpl(FeatureRepository featureRepository, OptionService optionService){
        this.featureRepository = featureRepository;
        this.optionService = optionService;
    }

    @Override
    public Page<FeatureDTO> findAll(FeatureSearchDTO search, Pageable pageable){
        FeatureSearch featureSearch = new FeatureSearch(search);
        Page<Feature> featuresPage = featureRepository.findAll(featureSearch,pageable);
        return featuresPage.map(FeatureMapper::toDto);
    }

    @Override
    public FeatureDTO save(SaveFeatureDTO saveFeatureDTO){
        Option option = optionService.findByIdEntity(saveFeatureDTO.getOptionId());
        Feature feature = FeatureMapper.toEntity(saveFeatureDTO, option);
        return FeatureMapper.toDto(featureRepository.save(feature));
    }

    @Override
    public FeatureDTO findById(Long id){
        return FeatureMapper.toDto(this.findByIdEntity(id));
    }

    @Override
    public FeatureDTO update(Long id, SaveFeatureDTO saveFeatureDTO){
       Feature feature = this.findByIdEntity(id);
       Option option = optionService.findByIdEntity(saveFeatureDTO.getOptionId());

       FeatureMapper.updateEntity(feature, saveFeatureDTO, option);

       return FeatureMapper.toDto(featureRepository.save(feature));
    }

    @Override
    public void delete(Long id){
       Feature feature = this.findByIdEntity(id);
        featureRepository.delete(feature);
    }

    @Override
    public Feature findByIdEntity(Long id){
        return featureRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("feature con ID: " + id + " no encontrada"));

    }


}
