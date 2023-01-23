package com.store.omega.api.middleware;

import com.store.omega.business.dto.PurchaseDTO;
import com.store.omega.domain.models.Purchase;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import reactor.core.publisher.Mono;


@Service
public class EventBus {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson = new Gson();

    public EventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private void convertAndSend(String routingKey, byte[] target) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                routingKey,
                target
        );
    }

    public void publishNewSale(PurchaseDTO newPurchaseDTO) {
                convertAndSend(
                        RabbitConfig.SALES_QUEUE_KEY,
                        gson.toJson(newPurchaseDTO).getBytes()
        );
    }
}
