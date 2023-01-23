package com.store.omega.business.usecases;

import com.store.omega.api.middleware.EventBus;
import com.store.omega.business.businessobjects.DetailedPurchasedProduct;
import com.store.omega.business.businessobjects.ProductBO;
import com.store.omega.business.businessobjects.PurchaseBO;
import com.store.omega.business.businessobjects.PurchaseNotification;
import com.store.omega.business.dto.PurchaseDTO;
import com.store.omega.domain.models.Purchase;
import com.store.omega.persistence.InventoryRepository;
import com.store.omega.persistence.PurchasesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class PerformPurchase {
    private final InventoryRepository inventoryRepository;
    private final PurchasesRepository purchasesRepository;

    private final EventBus eventBus;

    public PerformPurchase(InventoryRepository inventoryRepository, PurchasesRepository purchasesRepository, EventBus eventBus) {
        this.inventoryRepository = inventoryRepository;
        this.purchasesRepository = purchasesRepository;
        this.eventBus = eventBus;
    }

    public Mono<PurchaseDTO> performPurchase(Mono<PurchaseDTO> newPurchase){

        PurchaseNotification purchaseNotification = new PurchaseNotification();

        return newPurchase.map(purchaseDTO -> {

            PurchaseBO purchaseBO = new PurchaseBO(purchaseDTO);

            purchaseNotification.setCustomerName(purchaseDTO.getCustomerName());
            purchaseNotification.setIdType(purchaseDTO.getIdType());
            purchaseNotification.setCxId(purchaseDTO.getCxId());
            purchaseNotification.setDateTime(purchaseBO.getDateTime());

            return purchaseBO;
        })
                .doOnNext(purchaseBO -> purchaseBO.getPurchasedProducts().forEach(
                        purchasedProduct -> {
                            this.inventoryRepository
                                    .getProductById(purchasedProduct.getId())
                                    .map(product -> {
                                        ProductBO productBO = new ProductBO(product);

                                        purchaseNotification.appendDetailedProduct(
                                                new DetailedPurchasedProduct(
                                                        product.getId(),
                                                        purchasedProduct.getAmount(),
                                                        productBO.getName())
                                        );

                                        return productBO;
                                    })
                                    .subscribe(productBO -> {
                                        try {
                                            productBO.setInInventory(purchasedProduct.getAmount());
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                        if (!productBO.isEnabled() || purchasedProduct.getAmount() > productBO.getMax() || purchasedProduct.getAmount() < productBO.getMin()) {
                                            throw new RuntimeException("Error: Product not available");
                                        }
                                        this.inventoryRepository.modifyProductInventory(
                                                        productBO.getId(),
                                                        productBO.getInInventory())
                                                .subscribe();
                                    });
                        }
                ))
                .onErrorResume(throwable -> Mono.error(new Throwable(HttpStatus.NOT_ACCEPTABLE.toString())))
                .flatMap(purchaseBO -> this.purchasesRepository.createPurchase(new Purchase(purchaseBO)))
                .map(PurchaseDTO::new)
                .doOnSuccess(purchaseDTO -> eventBus.publishNewSale(purchaseNotification));

    }

}
