package com.ecommerce.ecommerce.service.paymentstatus;

import com.ecommerce.ecommerce.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RejectedPaymentProcessor implements PaymentStatusProcessor {

    private static final Logger log = LoggerFactory.getLogger(RejectedPaymentProcessor.class);

    @Override
    public OrderStatus getSupportedStatus(){
        return OrderStatus.REJECTED;
    }

    @Override
    public void process(String orderReference){
        log.info("❌ Pago rechazado para orden {}", orderReference);
        // Lógica de rechazo
        //Aca se podran enviar notificaciones o correos a los usuarios para confirmar el pago y hacer diferentes operaciones
    }
}
