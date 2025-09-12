package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;

public interface OrderService {

    OrderDTO create(SaveOrderDTO saveOrderDTO);
}
