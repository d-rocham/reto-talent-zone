package com.store.omega.business.usecases;

import com.store.omega.business.businessobjects.ProductBO;
import com.store.omega.business.dto.ProductDTO;
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

    public Mono<ProductDTO> createNewProduct(Mono<ProductDTO> newProduct) {
        return newProduct.map(ProductBO::new)
                .flatMap(productBO -> this.inventoryRepository
                        .createProduct(new Product(productBO)))
                .map(ProductDTO::new);
    }
}
