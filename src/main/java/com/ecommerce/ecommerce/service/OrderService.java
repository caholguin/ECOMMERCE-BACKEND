package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {

    OrderDTO create(SaveOrderDTO saveOrderDTO) throws JsonProcessingException;

    OrderDTO findById(Long id);

    OrderDTO updateStatus(Long id, int status);

    OrderDTO updateStatusForPaid(Long id);
}
