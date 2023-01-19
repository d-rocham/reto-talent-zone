package com.store.omega.usecases;

import com.store.omega.domain.models.Purchase;
import com.store.omega.persistence.PurchasesRepository;
import reactor.core.publisher.Mono;


public class FindPurchaseById {
    private final PurchasesRepository purchasesRepository;

    public FindPurchaseById(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    public Mono<Purchase> findPurchaseById(String targetId) {
        return this.purchasesRepository.getPurchaseById(targetId);
    }
}
