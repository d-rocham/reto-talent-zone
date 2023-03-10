package com.store.omega.persistence;

import com.store.omega.domain.models.Purchase;
import com.store.omega.persistence.repository.Purchases;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PurchasesRepository implements Purchases {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public PurchasesRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Flux<Purchase> getAllPurchases() {
        return reactiveMongoTemplate
                .findAll(Purchase.class);
    }

    @Override
    public Mono<Purchase> getPurchaseById(String targetId) {
        Query idQuery = new Query(Criteria.where("id").is(targetId));

        return reactiveMongoTemplate
                .findOne(idQuery, Purchase.class);
    }

    @Override
    public Flux<Purchase> getPurchasesByCx(String cxName) {
        Query cxQuery = new Query(Criteria.where("customerName").is(cxName));

        return reactiveMongoTemplate
                .find(cxQuery, Purchase.class);

    }

    @Override
    public Mono<Purchase> createPurchase(Purchase newPurchase) {
        return reactiveMongoTemplate
                .save(newPurchase);
    }
}
