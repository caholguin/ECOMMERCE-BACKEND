package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.request.PaymentDTO;
import com.ecommerce.ecommerce.dto.response.PaymentResponseDTO;
import com.ecommerce.ecommerce.service.PaymentService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${mercadopago.access.token}")
    private String accessToken;

    @Override
    public PaymentResponseDTO createPayment(PaymentDTO paymentDTO) throws MPException, MPApiException{
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
                                            .build())
                            .externalReference(String.valueOf(paymentDTO.getOrderId()))
                            .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            return new PaymentResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail(),
                    createdPayment.getExternalReference()
            );

        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw apiException;
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }
}