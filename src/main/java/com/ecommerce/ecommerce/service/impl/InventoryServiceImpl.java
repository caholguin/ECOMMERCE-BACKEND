package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.mapper.OrderMapper;
import com.ecommerce.ecommerce.service.InventoryService;
import com.ecommerce.ecommerce.service.OrderService;
import com.ecommerce.ecommerce.service.VariantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;
    private final VariantService variantService;


    public InventoryServiceImpl(OrderService orderService, ObjectMapper objectMapper, VariantService variantService){
        this.orderService = orderService;
        this.objectMapper = objectMapper;
        this.variantService = variantService;
    }

    @Override
    public void discountStock(Long orderId){

        Order order = this.orderService.findByIdEntity(orderId);

        try {
            List<OrderDTO.CartItemDTO> items = objectMapper.readValue(
                    order.getContent(),
                    new TypeReference<List<OrderDTO.CartItemDTO>>() {}
            );

            items.forEach(item -> {
                this.variantService.discountStock(item.getVariantId(),item.getAmount());
            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al deserializar el contenido de la orden", e);
        }
    }

    @Override
    public void increaseStock(Long orderId){

        Order order = this.orderService.findByIdEntity(orderId);

        try {
            List<OrderDTO.CartItemDTO> items = objectMapper.readValue(
                    order.getContent(),
                    new TypeReference<List<OrderDTO.CartItemDTO>>() {}
            );

            items.forEach(item -> {
                this.variantService.increaseStock(item.getVariantId(),item.getAmount());
            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al deserializar el contenido de la orden", e);
        }
    }
}