package com.store.delta.web;

import com.google.gson.Gson;
import com.store.delta.notifications.DetailedPurchasedProduct;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Component
@ServerEndpoint("/liveSales/{correlationId}")
public class SalesSocketController {
    private static Map<String, Map<String, Session>> sessions;
    private final Gson gson = new Gson();

    public SalesSocketController() {
        if (Objects.isNull(sessions)) {
            sessions = new ConcurrentHashMap<>();
        }
    }

    private static void broadcastJSON(String JSONMessage, String targetCorrelationId) {
        sessions.get(targetCorrelationId)
                .values().forEach(session -> {
                    try {
                        session.getAsyncRemote().sendText(JSONMessage);
                    } catch (RuntimeException runtimeException) {
                        log.error(runtimeException.getMessage());
                    }
                });
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("correlationId") String correlationId) {

        Map<String, Session> sessionMap = sessions.getOrDefault(correlationId, new HashMap<>());

        sessionMap.put(session.getId(), session);
        sessions.put(correlationId, sessionMap);
    }

    @OnClose
    public void onClose(Session session, @PathParam("correlationId") String correlationId) {
        sessions
                .get(correlationId)
                .remove(session.getId());
    }

    @OnError
    public void onError(Session session, @PathParam("correlationId") String correlationId, Throwable throwable) {
        sessions
                .get(correlationId)
                .remove(session.getId());

        log.error(throwable.getMessage());
    }

    public void submitNewSale(String correlationId, DetailedPurchasedProduct detailedPurchasedProduct){
        if (Objects.nonNull(correlationId) && sessions.containsKey(correlationId)) {
            broadcastJSON(gson.toJson(detailedPurchasedProduct), correlationId);
        }
    }

}
