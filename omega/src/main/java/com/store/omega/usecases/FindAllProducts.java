package com.store.omega.usecases;

import com.store.omega.domain.Product;
import com.store.omega.persistence.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FindAllProducts {
    private final InventoryRepository inventoryRepository;

    public FindAllProducts(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Flux<Product> findAllProducts() {
        return  this.inventoryRepository.getAllProducts();
    }
}
