package com.ecommerce.ecommerce.service.paymentstatus;


import com.ecommerce.ecommerce.enums.OrderStatus;

public interface PaymentStatusProcessor {
    OrderStatus getSupportedStatus();
    void process(String orderReference);
}
