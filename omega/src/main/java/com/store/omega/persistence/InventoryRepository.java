package com.store.omega.persistence;

import com.store.omega.domain.Product;
import com.store.omega.persistence.repository.Inventory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class InventoryRepository implements Inventory {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public InventoryRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
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
                .findOne(nameQuery, Product.class);
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
    public Mono<Product> modifyProductInventory(int productId, int updatedInventoryAmount) {
        Query idQuery = new Query(Criteria.where("id").is(productId));

        Update update = new Update();

        return reactiveMongoTemplate
                .findOne(idQuery, Product.class)
                .flatMap(product -> {
                    update.set("inInventory", updatedInventoryAmount);

                    return reactiveMongoTemplate
                            .findAndModify(idQuery, update, Product.class);

                });
    }

    @Override
    public void deleteProduct(int id) {
        Query idQuery = new Query(Criteria.where("id").is(id));

        reactiveMongoTemplate.remove(idQuery, Product.class);
    }

}
