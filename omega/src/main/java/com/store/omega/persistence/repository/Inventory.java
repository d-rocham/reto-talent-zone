package com.store.omega.persistence.repository;

import com.store.omega.persistence.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Inventory {
    Flux<Product> getAllProducts();
    Mono<Product> getProductByName(String productName);
    Mono<Product> getProductById(int id);

    Mono<Product> createProduct(Product newProduct);

    Mono<Product> modifyProductInventory(int productId, int updatedInventoryAmount);

    void deleteProduct(int id);
}
