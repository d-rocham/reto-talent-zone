package com.store.omega.persistence;

import com.store.omega.domain.models.Product;
import com.store.omega.persistence.repository.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class InventoryRepository implements Inventory {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public InventoryRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    private static void logError(Throwable error) {
        log.error(error.getMessage());
    }

    private static void logSuccessfulOperation(String successMessage) {
        log.info(successMessage);
    }

    @Override
    public Flux<Product> getAllProducts() {
        return reactiveMongoTemplate
                .findAll(Product.class);
    }

    @Override
    public Mono<Product> getProductByName(String productName) {
        Query nameQuery = new Query(Criteria.where("name").is(productName));

        return reactiveMongoTemplate
                .findOne(nameQuery, Product.class)
                .switchIfEmpty(Mono.error(new IllegalAccessException("Product with requested name was not found.")))
                .doOnError(InventoryRepository::logError)
                .doOnSuccess(e -> logSuccessfulOperation("Product found"));
    }

    @Override
    public Mono<Product> getProductById(int id) {
        Query idQuery = new Query(Criteria.where("id").is(id));

        return reactiveMongoTemplate.
                findOne(idQuery, Product.class);

    }

    @Override
    public Mono<Product> createProduct(Product newProduct) {
        return reactiveMongoTemplate
                .save(newProduct);
    }

    @Override
    public Mono<Product> modifyProductInventory(String productId, int updatedInventoryAmount) {
        Query idQuery = new Query(Criteria.where("id").is(productId));

        Update update = new Update();

        return reactiveMongoTemplate
                .findById(productId, Product.class)
                .flatMap(product -> {
                    update.set("inInventory", updatedInventoryAmount);

                    return reactiveMongoTemplate
                            .findAndModify(idQuery, update, Product.class);
                });
    }

    @Override
    public void deleteProduct(String id) {
        reactiveMongoTemplate
                .findAndRemove(new Query(Criteria.where("_id").is(id)), Product.class)
                .subscribe(product -> log.info(product.getName()));

    }
}