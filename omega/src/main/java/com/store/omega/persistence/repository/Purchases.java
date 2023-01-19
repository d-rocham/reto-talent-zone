package com.store.omega.persistence.repository;

import com.store.omega.persistence.models.Purchase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Purchases {
    Flux<Purchase> getAllPurchases();
    Mono<Purchase> getPurchaseById();
    Flux<Purchase> getPurchasesByCx();
    Mono<Purchase> createPurchase();

}
