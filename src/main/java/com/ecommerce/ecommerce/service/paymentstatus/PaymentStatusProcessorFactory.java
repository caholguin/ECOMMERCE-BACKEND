package com.ecommerce.ecommerce.service.paymentstatus;

import com.ecommerce.ecommerce.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentStatusProcessorFactory {

    private static final Logger log = LoggerFactory.getLogger(PaymentStatusProcessorFactory.class);

    private final Map<OrderStatus, PaymentStatusProcessor> processors;

    public PaymentStatusProcessorFactory(List<PaymentStatusProcessor> processorList) {
        this.processors = new HashMap<>();
        for (PaymentStatusProcessor processor : processorList) {
            processors.put(processor.getSupportedStatus(), processor);
        }
    }

    public PaymentStatusProcessor getProcessor(OrderStatus status) {
        return processors.getOrDefault(status, new PaymentStatusProcessor() {
            @Override
            public OrderStatus getSupportedStatus() {
                return status;
            }

            @Override
            public void process(String orderReference) {
                log.info("📝 Estado {} no tiene lógica específica", status);
            }
        });
    }
}
