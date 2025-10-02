package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.request.search.OrderSearchDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    OrderDTO create(SaveOrderDTO saveOrderDTO) throws JsonProcessingException;

    OrderDTO findById(Long id);

    void updateOrderStatus(Long id, int status, Long paymentId);

    Order findByIdEntity(Long id);

    List<OrderDTO> findByUserId(Long id);

    Page<OrderDTO> findAll(OrderSearchDTO orderSearchDTO, Pageable pageable);

    OrderDTO updateStatus(Long id, int status);
}
