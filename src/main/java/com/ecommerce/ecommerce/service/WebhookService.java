package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.enums.OrderStatus;
import com.ecommerce.ecommerce.service.paymentstatus.PaymentStatusProcessor;
import com.ecommerce.ecommerce.service.paymentstatus.PaymentStatusProcessorFactory;
import com.mercadopago.resources.payment.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WebhookService {

    private static final Logger log = LoggerFactory.getLogger(WebhookService.class);

    private final MercadoPagoService mercadoPagoService;
    private final OrderService orderService;
    private final PaymentStatusProcessorFactory processorFactory;
    private final PaymentNotificationService orderNotificationService;

    public WebhookService(MercadoPagoService mercadoPagoService, OrderService orderService, PaymentStatusProcessorFactory processorFactory, PaymentNotificationService orderNotificationService){
        this.mercadoPagoService = mercadoPagoService;
        this.orderService = orderService;
        this.processorFactory = processorFactory;
        this.orderNotificationService = orderNotificationService;
    }


    public void processNotification(Map<String, Object> payload) {
        try {
            String type = (String) payload.get("type");
            Map<String, Object> data = (Map<String, Object>) payload.get("data");

            if (!"payment".equals(type) || data == null) {
                return;
            }

            Object paymentIdObj = data.get("id");
            if (paymentIdObj == null) {
                return;
            }

            Long paymentId = Long.valueOf(paymentIdObj.toString());
            Payment payment = mercadoPagoService.getPayment(paymentId);

            String orderReference = payment.getExternalReference();
            String statusStr = payment.getStatus();

            OrderStatus.fromMercadoPago(statusStr).ifPresent(orderStatus -> {
                orderService.updateOrderStatus(Long.valueOf(orderReference), orderStatus.getCode(),paymentId);

                // Notificar por websocket
                orderNotificationService.processPaymentNotification(Long.valueOf(orderReference), orderStatus.getCode());

                PaymentStatusProcessor processor = processorFactory.getProcessor(orderStatus);
                processor.process(orderReference);
            });
        } catch (Exception e) {
            log.error("❌ Error procesando webhook", e);
        }
    }
}

