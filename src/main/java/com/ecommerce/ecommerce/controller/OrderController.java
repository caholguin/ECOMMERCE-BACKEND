package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> create(@RequestBody @Valid SaveOrderDTO saveOrderDTO){
        OrderDTO address = orderService.create(saveOrderDTO);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
}
