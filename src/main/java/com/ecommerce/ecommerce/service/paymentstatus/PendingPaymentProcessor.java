package com.ecommerce.ecommerce.service.paymentstatus;

import com.ecommerce.ecommerce.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PendingPaymentProcessor implements PaymentStatusProcessor {

    private static final Logger log = LoggerFactory.getLogger(PendingPaymentProcessor.class);

    @Override
    public OrderStatus getSupportedStatus(){
        return OrderStatus.PENDING;
    }

    @Override
    public void process(String orderReference){
        log.info("⏳ Pago pendiente para orden {}", orderReference);
        // Lógica de pendiente
        //Aca se podran enviar notificaciones o correos a los usuarios para confirmar el pago y hacer diferentes operaciones
    }
}
