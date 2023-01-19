package com.store.omega.persistence.repository;

import com.store.omega.persistence.models.Purchase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Purchases {
    Flux<Purchase> getAllPurchases();
    Mono<Purchase> getPurchaseById(String targetId);
    Flux<Purchase> getPurchasesByCx(String targetCxId);
    Mono<Purchase> createPurchase(Purchase newPurchase);

}
