package com.store.omega.business.usecases;

import com.store.omega.domain.models.Purchase;
import com.store.omega.persistence.PurchasesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FindAllPurchases {
    private final PurchasesRepository purchasesRepository;

    public FindAllPurchases(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    public Flux<Purchase> findAllPurchases() {
        return this.purchasesRepository.getAllPurchases();
    }
}
