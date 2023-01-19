package com.store.omega.usecases;

import com.store.omega.domain.Product;
import com.store.omega.persistence.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FindProductByName {
    private final InventoryRepository inventoryRepository;

    public FindProductByName(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Mono<Product> findProudctByName(String productName) {
        return this.inventoryRepository.getProductByName(productName);
    }
}
