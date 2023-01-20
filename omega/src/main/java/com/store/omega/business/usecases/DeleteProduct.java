package com.store.omega.business.usecases;

import com.store.omega.persistence.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteProduct {
    private final InventoryRepository inventoryRepository;

    public DeleteProduct(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void deleteProduct(String targetId) {
        inventoryRepository.deleteProduct(targetId);
    }
}
