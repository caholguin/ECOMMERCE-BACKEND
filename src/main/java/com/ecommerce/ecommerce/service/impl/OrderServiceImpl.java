package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.response.AddressDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.OrderMapper;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    private final AddressService addressService;
    private final UserService userService;
    private final ProductService productService;
    private final VariantService variantService;

    public OrderServiceImpl(OrderRepository orderRepository, ObjectMapper objectMapper, AddressService addressService, UserService userService, ProductService productService, VariantService variantService){
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
        this.addressService = addressService;
        this.userService = userService;
        this.productService = productService;
        this.variantService = variantService;
    }

    @Override
    public OrderDTO create(SaveOrderDTO saveOrderDTO) throws JsonProcessingException{
        final double[] total = {0.0};

        saveOrderDTO.getContent().forEach(item -> {
            ProductDTO product = this.productService.findById(item.getProductId());
            //VariantDTO variantDTO = this.variantService.findById(item.getVariantId());

            Double priceWithDiscount = product.getPrice() - (product.getPrice() * product.getDiscount() / 100);

            double subtotal = priceWithDiscount * item.getAmount();

            total[0] += subtotal;
        });

        String contentJson = objectMapper.writeValueAsString(saveOrderDTO.getContent());

        Optional<AddressDTO> address = this.addressService.findByUserIdAndIsDefaultTrue(saveOrderDTO.getUserId());

        User user = userService.findByIdEntity(saveOrderDTO.getUserId());

        String addressJson = address
                .map(a -> {
                    try {
                        return objectMapper.writeValueAsString(a);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error serializando dirección", e);
                    }
                })
                .orElse(null);

        Order order = new Order();
        order.setAddress(addressJson);
        order.setContent(contentJson);
        order.setPaymentId(saveOrderDTO.getPaymentId());
        order.setPaymentMethod(1);
        order.setTotal(total[0]);
        order.setUser(user);

        Order saveOrder = orderRepository.save(order);
        return null;
    }

    @Override
    public OrderDTO findById(Long id){
        return OrderMapper.toDto(this.findByIdEntity(id));
    }

    @Override
    public void updateOrderStatus(Long id, int status){
        Order order = this.findByIdEntity(id);

        order.setStatus(status);
        this.orderRepository.save(order);
    }

    @Override
    public Order findByIdEntity(Long id){
        return orderRepository.findById(id) .orElseThrow(() -> new ObjectNotFoundException("Orden con ID: " + id + " no encontrada"));
    }



}
