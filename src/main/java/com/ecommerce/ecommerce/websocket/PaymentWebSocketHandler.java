package com.ecommerce.ecommerce.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class PaymentWebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("Nueva conexión WebSocket establecida: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Conexión WebSocket cerrada: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Manejar mensajes del cliente si es necesario
        System.out.println("Mensaje recibido: " + message.getPayload());
    }

    // Método para enviar actualizaciones de pago a todos los clientes conectados
    public void sendPaymentUpdate(Long orderId, int status) {
        String message = String.format("{\"orderId\":\"%s\",\"status\":\"%s\"}",
                orderId, status);

        synchronized (sessions) {
            sessions.removeIf(session -> {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(new TextMessage(message));
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    System.err.println("Error enviando mensaje WebSocket: " + e.getMessage());
                    return true;
                }
            });
        }
    }
}
