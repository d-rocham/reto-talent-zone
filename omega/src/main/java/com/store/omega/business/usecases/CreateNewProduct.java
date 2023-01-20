package com.store.omega.business.usecases;

import com.store.omega.domain.models.Product;
import com.store.omega.persistence.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateNewProduct {
    private final InventoryRepository inventoryRepository;

    public CreateNewProduct(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Mono<Product> createNewProduct(Product newProduct) {
        return inventoryRepository.createProduct(newProduct);
    }
}
