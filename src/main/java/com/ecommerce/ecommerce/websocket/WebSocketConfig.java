package com.ecommerce.ecommerce.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private PaymentWebSocketHandler paymentWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(paymentWebSocketHandler, "/ws/payments")
                .setAllowedOrigins("http://localhost:4200") // URL de front
                .setAllowedOriginPatterns("*"); // Para desarrollo
    }
}
