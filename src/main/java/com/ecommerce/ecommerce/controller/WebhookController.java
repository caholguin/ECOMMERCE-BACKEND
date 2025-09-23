package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.service.WebhookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService){
        this.webhookService = webhookService;
    }

    //metodo para simular envios de mercado pago
/*   @PostMapping()
    public ResponseEntity<String> simulateMercadoPagoWebhook(
            @RequestParam Long orderId,
            @RequestParam String status) {

        // Simular el payload que envía MercadoPago
        Map<String, Object> payload = new HashMap<>();
        payload.put("type", "payment");

        Map<String, Object> data = new HashMap<>();
        data.put("id", "1341097737"); // ID ficticio del pago
        payload.put("data", data);

        // Llamar directamente a tu método original
        return this.receiveNotification(payload);
    }*/


    @PostMapping()
    public ResponseEntity<String> receiveNotification(@RequestBody Map<String, Object> payload) {
        webhookService.processNotification(payload);
        return ResponseEntity.ok("ok"); // siempre devolver 200 a MP
    }
}
