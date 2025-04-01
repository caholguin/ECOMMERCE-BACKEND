package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.PaymentDTO;
import com.ecommerce.ecommerce.dto.response.PaymentResponseDTO;
import com.ecommerce.ecommerce.service.PaymentService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.RoundingMode;

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

    @Value("${mercadopago.access.token}")
    private String accessToken;



    /*@PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody PaymentDTO paymentDTO)  {
        try {
            MercadoPagoConfig.setAccessToken(accessToken);

            PaymentClient paymentClient = new PaymentClient();

            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(paymentDTO.getTransactionAmount().setScale(2, RoundingMode.HALF_UP))
                            .token(paymentDTO.getToken())
                            .description(paymentDTO.getProductDescription())
                            .installments(paymentDTO.getInstallments())
                            .paymentMethodId(paymentDTO.getPaymentMethodId())
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(paymentDTO.getPayer().getEmail())
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type(paymentDTO.getPayer().getIdentification().getType())
                                                            .number(paymentDTO.getPayer().getIdentification().getNumber())
                                                            .build())
                                            .build())
                            .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            PaymentResponseDTO responseDTO = new PaymentResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail());

            return ResponseEntity.ok(responseDTO);
        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PaymentResponseDTO(null, "ERROR", apiException.getApiResponse().getContent()));
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PaymentResponseDTO(null, "ERROR", exception.getMessage()));
        }
    }*/


}
