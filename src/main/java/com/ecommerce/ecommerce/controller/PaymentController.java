package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.request.PaymentDTO;
import com.ecommerce.ecommerce.dto.response.PaymentResponseDTO;
import com.ecommerce.ecommerce.entity.Order;
import com.ecommerce.ecommerce.enums.OrderStatus;
import com.ecommerce.ecommerce.service.PaymentService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RestController
@RequestMapping("/process-payment")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Value("${mercadopago.access.token}")
    private String accessToken;

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> payment(@RequestBody @Valid PaymentDTO paymentDTO) throws MPException, MPApiException{
        PaymentResponseDTO payment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }

    @PostMapping("/webhooks")
    public ResponseEntity<String> receiveNotification(@RequestBody Map<String, Object> payload) {
        try {
            String type = (String) payload.get("type");
            Map<String, Object> data = (Map<String, Object>) payload.get("data");

            // Solo procesar pagos
            if (!"payment".equals(type) || data == null) {
                return ResponseEntity.ok("ok");
            }

            Long paymentId = Long.valueOf(data.get("id").toString());

            // Obtener info del pago desde MP
            MercadoPagoConfig.setAccessToken(accessToken);
            PaymentClient client = new PaymentClient();
            Payment payment = client.get(paymentId);

            String orderReference = payment.getExternalReference();
            String statusStr = payment.getStatus();

            // Convertir status de MercadoPago → OrderStatus
            OrderStatus.fromMercadoPago(statusStr).ifPresentOrElse(orderStatus -> {
                logger.info("💾 Actualizando orden {} a estado {} ({})",
                        orderReference, orderStatus.getCode(), orderStatus.getDescription());

                // Aquí actualizas tu BD con el code del enum
                // updateOrderInDatabase(orderReference, orderStatus.getCode());

                logStatusChange(orderReference, orderStatus);

            }, () -> logger.error("❌ Estado de MercadoPago no reconocido: {}", statusStr));

            return ResponseEntity.ok("ok");

        } catch (Exception e) {
            logger.error("❌ Error en webhook", e);
            // Siempre devolver 200 OK a MP para evitar reintentos infinitos
            return ResponseEntity.ok("ok");
        }
    }

    private void logStatusChange(String orderRef, OrderStatus status) {
        logger.info("✅ Orden {} actualizada a {} ({})",
                orderRef, status.getCode(), status.getDescription());

        // Acciones según el estado
        switch (status) {
            case APPROVED:
                logger.info("🎉 Pago confirmado para orden {}", orderRef);
                // sendConfirmationEmail(orderRef);
                break;

            case REJECTED:
            case CANCELLED:
                logger.info("❌ Pago falló para orden {}", orderRef);
                // sendPaymentFailedEmail(orderRef);
                break;

            case REFUNDED:
            case PARTIALLY_REFUNDED:
            case CHARGED_BACK:
                logger.info("💸 Reembolso procesado para orden {}", orderRef);
                // sendRefundEmail(orderRef);
                break;

            case SHIPPED:
                logger.info("📦 Orden {} enviada", orderRef);
                break;

            case DELIVERED:
                logger.info("📬 Orden {} entregada", orderRef);
                break;

            case COMPLETED:
                logger.info("✅ Orden {} completada", orderRef);
                break;

            default:
                logger.info("ℹ️ Estado {} sin acción específica", status);
                break;
        }
    }
}
