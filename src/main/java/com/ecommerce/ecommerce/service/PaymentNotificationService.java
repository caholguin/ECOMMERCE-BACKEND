package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.websocket.PaymentWebSocketHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentNotificationService {

    private final PaymentWebSocketHandler webSocketHandler;

    public PaymentNotificationService(PaymentWebSocketHandler webSocketHandler){
        this.webSocketHandler = webSocketHandler;
    }

    // Este método se llama desde tu webhook de MercadoPago
    public void processPaymentNotification(Long orderId, int status) {
        // Aquí puedes hacer la lógica adicional (guardar en BD, etc.)

        // Enviar actualización via WebSocket
        webSocketHandler.sendPaymentUpdate(orderId, status);
    }
}
