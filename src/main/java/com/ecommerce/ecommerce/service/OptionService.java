package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.OptionSearchDTO;
import com.ecommerce.ecommerce.dto.request.SaveOptionDTO;
import com.ecommerce.ecommerce.dto.response.OptionDTO;
import com.ecommerce.ecommerce.entity.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OptionService {

    Page<OptionDTO> findAll(OptionSearchDTO optionSearchDTO,Pageable pageable);

    OptionDTO save(SaveOptionDTO saveOptionDTO);

    OptionDTO findById(Long id);

    OptionDTO update(Long id, SaveOptionDTO saveOptionDTO);

    void delete(Long id);

    Option findByIdEntity(Long id);

    List<OptionDTO> findBySubcategory(Long subcategoryId);
}
