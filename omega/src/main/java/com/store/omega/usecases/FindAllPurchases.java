package com.store.omega.usecases;

import com.store.omega.domain.models.Purchase;
import com.store.omega.persistence.PurchasesRepository;
import reactor.core.publisher.Flux;

public class FindAllPurchases {
    private final PurchasesRepository purchasesRepository;

    public FindAllPurchases(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    public Flux<Purchase> findAllPurchases() {
        this.purchasesRepository.getAllPurchases();
    }
}
