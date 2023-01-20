package com.store.omega.persistence.repository;

import com.store.omega.domain.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Inventory {
    Flux<Product> getAllProducts();
    Mono<Product> getProductByName(String productName);
    Mono<Product> getProductById(String id);

    Mono<Product> createProduct(Product newProduct);

    Mono<Product> modifyProductInventory(String productId, int updatedInventoryAmount);

    void deleteProduct(String id);
}