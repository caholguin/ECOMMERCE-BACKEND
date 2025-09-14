package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.response.AddressDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.dto.response.ProductDTO;
import com.ecommerce.ecommerce.dto.response.VariantDTO;
import com.ecommerce.ecommerce.entity.City;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.enums.OrderStatus;
import com.ecommerce.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.ecommerce.mapper.OrderMapper;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
        //en principio esta parte solo va a afectar si el pago se realiza pero por ahora sera solo para descontar stocks
        saveOrderDTO.getContent().forEach(item -> {
       /*     System.out.println("variantId = " + item.getVariantId());
            System.out.println("productId = " + item.getProductId());
            System.out.println("amount = " + item.getAmount());
            System.out.println("unitPrice = " + item.getUnitPrice());*/

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

        System.out.println("contentJson = " + contentJson);
        System.out.println("addressJson = " + addressJson);

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
    public OrderDTO updateStatus(Long id, int status){
        return null;
    }

    @Override
    public OrderDTO updateStatusForPaid(Long id){

        Order order = this.findByIdEntity(id);

        try {
            List<OrderDTO.CartItemDTO> items = objectMapper.readValue(
                    order.getContent(),
                    new TypeReference<List<OrderDTO.CartItemDTO>>() {}
            );

            items.forEach(item -> {
                this.variantService.discountStock(item.getVariantId(),item.getAmount());
            });

            order.setStatus(2);
            orderRepository.save(order);

            return OrderMapper.toDto(order);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al deserializar el contenido de la orden", e);
        }
    }

    private Order findByIdEntity(Long id){
        return orderRepository.findById(id) .orElseThrow(() -> new ObjectNotFoundException("Ciudad con ID: " + id + " no encontrada"));
    }

}
