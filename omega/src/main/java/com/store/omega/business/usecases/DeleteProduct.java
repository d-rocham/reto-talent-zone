package com.store.omega.business.usecases;

import com.store.omega.persistence.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteProduct {
    private final InventoryRepository inventoryRepository;

    public DeleteProduct(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Mono<Void> deleteProduct(String targetId) {
        inventoryRepository.deleteProduct(targetId);

        return Mono.empty();
    }
}
