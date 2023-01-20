package com.store.omega.business.usecases;

import com.store.omega.business.dto.ProductDTO;
import com.store.omega.persistence.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FindAllProducts {
    private final InventoryRepository inventoryRepository;

    public FindAllProducts(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Flux<ProductDTO> findAllProducts() {
        return this.inventoryRepository.getAllProducts().map(ProductDTO::new);
    }
}
