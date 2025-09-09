package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.PaymentDTO;
import com.ecommerce.ecommerce.dto.response.PaymentResponseDTO;
import com.ecommerce.ecommerce.service.PaymentService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process-payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> payment(@RequestBody @Valid PaymentDTO paymentDTO) throws MPException, MPApiException{
        PaymentResponseDTO payment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
}
