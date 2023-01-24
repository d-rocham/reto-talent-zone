package com.store.delta.queue;

import com.google.gson.Gson;
import com.store.delta.notifications.PurchaseNotification;
import com.store.delta.web.SalesSocketController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class QueueConsumer {
    private final Gson gson = new Gson();
    private final SalesSocketController salesSocketController;

    public QueueConsumer(SalesSocketController salesSocketController) {
        this.salesSocketController = salesSocketController;
    }

    public static final String SALES_QUEUE = "sales-queue";

    @RabbitListener(queues = SALES_QUEUE)
    public void listenNewSales(String json) {
        PurchaseNotification purchaseNotification = gson.fromJson(json, PurchaseNotification.class);
        salesSocketController.submitNewSale("mainspace", purchaseNotification);
    }
}
