package com.ecommerce.ecommerce.enums;


import java.util.Optional;

public enum OrderStatus {
    // Estados de pago (coinciden con mapMercadoPagoStatus)
    PENDING(1,"Pendiente"),
    IN_PROCESS(1,"En proceso (lo tratamos igual que pendiente)"),
    IN_MEDIATION(1,"En mediación (también como pendiente)"),
    APPROVED(2,"Pagado"),
    AUTHORIZED(3,"Autorizado (fondos reservados)"),
    PENDING_CAPTURE(3,"Pendiente de captura (similar a autorizado)"),
    REFUNDED(4,"Reembolsado totalmente"),
    PARTIALLY_REFUNDED(4,"Reembolsado parcialmente"),
    CHARGED_BACK(4,"Contracargo"),
    REJECTED(6,"Fallido"),
    CANCELLED(6,"Cancelado"),

    // Estados de logística / fulfilment
    PROCESSING(7, "Procesando"),
    SHIPPED(8, "Enviada"),
    DELIVERED(9, "Entregada"),
    COMPLETED(10, "Completada (orden cerrada)");


    private final int code;
    private final String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus fromCode(int code) {
        for (OrderStatus status : values()) {
            if (status.code == code) return status;
        }
        throw new IllegalArgumentException("Código de estado inválido: " + code);
    }

    public static Optional<OrderStatus> fromMercadoPago(String mpStatus) {
        try {
            return Optional.of(OrderStatus.valueOf(mpStatus.toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            return Optional.empty();
        }
    }

}