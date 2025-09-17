package com.ecommerce.ecommerce.service.paymentstatus;

import com.ecommerce.ecommerce.controller.PaymentController;
import com.ecommerce.ecommerce.enums.OrderStatus;
import com.ecommerce.ecommerce.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApprovedPaymentProcessor implements PaymentStatusProcessor{

    private static final Logger log = LoggerFactory.getLogger(ApprovedPaymentProcessor.class);

    private final InventoryService inventoryService;

    public ApprovedPaymentProcessor(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @Override
    public OrderStatus getSupportedStatus(){
        return OrderStatus.APPROVED;
    }

    @Override
    public void process(String orderReference){
        log.info("✅ Pago aprobado para orden {}", orderReference);

        //Aca se podran enviar notificaciones o correos a los usuarios para confirmar el pago y hacer diferentes operaciones
        this.inventoryService.discountStock(Long.valueOf(orderReference));

    }
}
