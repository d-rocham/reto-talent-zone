package com.store.omega.api.middleware;

import com.store.omega.business.businessobjects.PurchaseNotification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;


@Service
public class EventBus {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson = new Gson();

    public EventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private void convertAndSend(String routingKey, byte[] target) {
        // routingKey provided as parameter to re-use this function in other future examples
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                routingKey,
                target
        );
    }

    public void publishNewSale(PurchaseNotification purchaseNotification) {
                convertAndSend(
                        RabbitConfig.SALES_QUEUE_KEY,
                        gson.toJson(purchaseNotification).getBytes()
        );
    }
}
