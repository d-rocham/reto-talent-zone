package com.store.omega.persistence.repository;

import com.store.omega.persistence.models.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Inventory {
    Flux<Product> GetAllProducts();
    Mono<Product> GetProductByName(String productName);
    Mono<Product> GetProductById(int id);

    Mono<Product> CreateProduct(Product newProduct);

    Mono<Product> ModifyProductInventory(int productId, int updatedInventoryAmount);

    Mono<Void> DeleteProduct(int id);
}