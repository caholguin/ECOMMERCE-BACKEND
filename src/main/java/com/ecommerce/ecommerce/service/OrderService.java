package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {

    OrderDTO create(SaveOrderDTO saveOrderDTO) throws JsonProcessingException;

    OrderDTO findById(Long id);

    void updateOrderStatus(Long id, int status, Long paymentId);

    Order findByIdEntity(Long id);
}
