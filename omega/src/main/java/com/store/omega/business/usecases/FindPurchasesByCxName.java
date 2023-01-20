package com.store.omega.business.usecases;

import com.store.omega.domain.models.Purchase;
import com.store.omega.persistence.PurchasesRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FindPurchasesByCxName {
    private final PurchasesRepository purchasesRepository;

    public FindPurchasesByCxName(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    public Flux<Purchase> findPurchasesByCxName(String cxName) {
        return this.purchasesRepository.getPurchasesByCx(cxName);
    }
}
