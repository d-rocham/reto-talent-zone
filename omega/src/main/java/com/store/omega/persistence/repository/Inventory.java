package com.store.omega.persistence.repository;

import com.store.omega.persistence.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Inventory {
    Flux<Product> GetAllProducts();
    Mono<Product> GetProductByName(String productName);
    Mono<Product> GetProductById(int id);

    Mono<Product> CreateProduct(String productName, int inInventory, boolean enabled, int min, int max);

    Mono<Product> ModifyProductInventory(int updatedInventoryAmount);

    Mono<Void> DeleteProduct(int id);
}
