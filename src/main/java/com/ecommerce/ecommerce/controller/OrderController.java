package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> create(@RequestBody @Valid SaveOrderDTO saveOrderDTO) throws JsonProcessingException{
        OrderDTO order = orderService.create(saveOrderDTO);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
        OrderDTO order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<OrderDTO>> findByUserId(@PathVariable Long id){
        List<OrderDTO> order = orderService.findByUserId(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
