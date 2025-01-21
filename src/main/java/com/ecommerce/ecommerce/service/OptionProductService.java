package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveOptionProductDTO;
import com.ecommerce.ecommerce.dto.request.SaveProductDTO;
import com.ecommerce.ecommerce.dto.response.OptionProductDTO;

public interface OptionProductService {

    OptionProductDTO save(SaveOptionProductDTO saveOptionProductDTO);

    void delete(Long id,Long featureId);

    OptionProductDTO update(SaveOptionProductDTO saveOptionProductDTO);

}
