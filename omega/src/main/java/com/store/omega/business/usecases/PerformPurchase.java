package com.store.omega.business.usecases;

import com.store.omega.business.businessobjects.ProductBO;
import com.store.omega.business.businessobjects.PurchaseBO;
import com.store.omega.business.dto.PurchaseDTO;
import com.store.omega.domain.models.Purchase;
import com.store.omega.persistence.InventoryRepository;
import com.store.omega.persistence.PurchasesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class PerformPurchase {
    private final InventoryRepository inventoryRepository;
    private final PurchasesRepository purchasesRepository;

    public PerformPurchase(InventoryRepository inventoryRepository, PurchasesRepository purchasesRepository) {
        this.inventoryRepository = inventoryRepository;
        this.purchasesRepository = purchasesRepository;
    }

    public Mono<PurchaseDTO> performPurchase(Mono<PurchaseDTO> newPurchase){
        return newPurchase.map(PurchaseBO::new)
                .doOnNext(purchaseBO -> purchaseBO.getPurchasedProducts().forEach(
                        purchasedProduct -> this.inventoryRepository
                                .getProductById(purchasedProduct.getId())
                                .map(ProductBO::new)
                                .doOnNext(productBO -> {
                                    try {
                                        productBO.setInInventory(purchasedProduct.getAmount());
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    if (!productBO.isEnabled() || purchasedProduct.getAmount() > productBO.getMax() || purchasedProduct.getAmount() < productBO.getMin()) {
                                        throw new RuntimeException("Error: Product not available");
                                    }})
                                .onErrorResume(throwable -> Mono.error(new Throwable(HttpStatus.NOT_ACCEPTABLE.toString())))
                                .map(productBO -> this.inventoryRepository
                                        .modifyProductInventory(productBO.getId(), productBO.getInInventory()))
                )).flatMap(purchaseBO -> this.purchasesRepository.createPurchase(new Purchase(purchaseBO)))
                .map(PurchaseDTO::new);
    }

}
