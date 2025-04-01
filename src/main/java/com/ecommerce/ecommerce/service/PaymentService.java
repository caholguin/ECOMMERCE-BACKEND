package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.request.PaymentDTO;
import com.ecommerce.ecommerce.dto.response.PaymentResponseDTO;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentDTO paymentDTO) throws MPException, MPApiException;
}
