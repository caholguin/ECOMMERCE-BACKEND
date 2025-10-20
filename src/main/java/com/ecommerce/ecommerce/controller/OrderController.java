package com.ecommerce.ecommerce.controller;
import com.ecommerce.ecommerce.dto.request.SaveOrderDTO;
import com.ecommerce.ecommerce.dto.request.search.OrderSearchDTO;
import com.ecommerce.ecommerce.dto.response.OrderDTO;
import com.ecommerce.ecommerce.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @GetMapping()
    public ResponseEntity<Page<OrderDTO>> findAll(Pageable pageable, @RequestParam(required = false) Long id, @RequestParam(required = false) Long status){

        OrderSearchDTO orderSearchDTO = new OrderSearchDTO(id,status);

        Page<OrderDTO> orders = orderService.findAll(orderSearchDTO,pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);
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
    public ResponseEntity<Page<OrderDTO>> findByUserId(Pageable pageable, @PathVariable Long id){
        Page<OrderDTO> orders = orderService.findByUserId(id,pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PatchMapping("update-status/{id}")
    public ResponseEntity<OrderDTO> updateStatus(@PathVariable Long id, @RequestBody @Valid SaveOrderDTO.updateStatus updateStatus){
        OrderDTO order = orderService.updateStatus(id,updateStatus.getStatus());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
