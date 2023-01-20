package com.store.omega.business.usecases;

import com.store.omega.domain.models.Purchase;
import com.store.omega.persistence.PurchasesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FindPurchaseById {
    private final PurchasesRepository purchasesRepository;

    public FindPurchaseById(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    public Mono<Purchase> findPurchaseById(String targetId) {
        return this.purchasesRepository.getPurchaseById(targetId);
    }
}
