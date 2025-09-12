package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO create(SaveOrderDTO saveOrderDTO){

        saveOrderDTO.getContent().forEach(item -> {
            System.out.println("variantId = " + item.getVariantId());
            System.out.println("productId = " + item.getProductId());
            System.out.println("amount = " + item.getAmount());
            System.out.println("unitPrice = " + item.getUnitPrice());
        });

        return null;
    }
}
