package com.ecommerce.ecommerce.mapper;

import com.ecommerce.ecommerce.dto.request.SaveCategoryDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Family;
import com.ecommerce.ecommerce.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static OrderDTO toDto(Order order){

        if(order == null) return null;

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());

        try {
            OrderDTO.AddressDTO addressDTO = objectMapper.readValue(
                    order.getAddress(),
                    OrderDTO.AddressDTO.class
            );
            orderDTO.setAddress(addressDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al deserializar la dirección de la orden", e);
        }

        try {
            List<OrderDTO.CartItemDTO> items = objectMapper.readValue(
                    order.getContent(),
                    new TypeReference<List<OrderDTO.CartItemDTO>>() {}
            );
            orderDTO.setContent(items);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al deserializar el contenido de la orden", e);
        }

        orderDTO.setPaymentId(order.getPaymentId());
        orderDTO.setPdfPath(order.getPdfPath());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotal(order.getTotal());
        orderDTO.setUser(UserMapper.toGetUserDto(order.getUser()));

        return orderDTO;
    }

    public static Category toEntity(SaveCategoryDTO saveCategoryDTO, Family family){

        if(saveCategoryDTO == null) return null;

        Category category = new Category();
        category.setName(saveCategoryDTO.getName());
        category.setIcon(saveCategoryDTO.getIcon());
        category.setFamily(family);

        return category;
    }

    public static void updateEntity(Category category, SaveCategoryDTO saveCategoryDTO,Family family){
        if(category == null || saveCategoryDTO == null) return;

        category.setName(saveCategoryDTO.getName());
        category.setIcon(saveCategoryDTO.getIcon());
        category.setFamily(family);
    }
}
