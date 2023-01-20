package com.store.omega.business.usecases;

import com.store.omega.business.businessobjects.ProductBO;
import com.store.omega.domain.models.Purchase;
import com.store.omega.persistence.InventoryRepository;
import com.store.omega.persistence.PurchasesRepository;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;


public class PerformPurchase {
    private final InventoryRepository inventoryRepository;
    private final PurchasesRepository purchasesRepository;

    public PerformPurchase(InventoryRepository inventoryRepository, PurchasesRepository purchasesRepository) {
        this.inventoryRepository = inventoryRepository;
        this.purchasesRepository = purchasesRepository;
    }

    private Mono<Boolean> isStockEnough(int productId, int intendedAmount) {

        return this.inventoryRepository.getProductById(productId)
                .map(product -> product.getInInventory() > intendedAmount);
    }

    public Mono<Purchase> performPurchase(Purchase newPurchase){

        newPurchase.getPurchasedProducts().stream().map(
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
                                throw new RuntimeException("Error: Product not available for sale");
                            }
                        })
                        .onErrorResume(throwable -> Mono.error(new Throwable(HttpStatus.NOT_ACCEPTABLE.toString())))
                        .map(productBO -> this.inventoryRepository
                                .modifyProductInventory(productBO.getId(), productBO.getInInventory()))

        );

        return purchasesRepository.createPurchase(newPurchase);
    }

}
