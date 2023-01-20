package com.store.omega.api;

import com.store.omega.business.dto.ProductDTO;
import com.store.omega.business.dto.PurchaseDTO;
import com.store.omega.business.usecases.*;
import com.store.omega.domain.models.Product;
import com.store.omega.domain.models.Purchase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QueryHandler {

    @Bean
    public RouterFunction<ServerResponse> manageProducts(
            FindAllProducts findAllProducts,
            FindProductByName findProductByName,
            CreateNewProduct createNewProduct,
            DeleteProduct deleteProduct
    ) {
        return
                route(GET("/products"),
                        request -> ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(
                                        BodyInserters.fromPublisher(findAllProducts.findAllProducts(), ProductDTO.class)
                                )
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).build()))
                        .and(route(GET("/product"),
                                request -> ServerResponse
                                        .ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(
                                                findProductByName.findProductByName(request.queryParam("name").get()),
                                                ProductDTO.class))
                                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
                                ))
                        .and(route(POST("/products"),
                                request -> ServerResponse
                                        .ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(
                                                        createNewProduct.createNewProduct(request.bodyToMono(ProductDTO.class)),
                                                ProductDTO.class))
                                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
                                ))
                        .and(route(DELETE("/product"),
                                request -> deleteProduct.deleteProduct(request.queryParam("id").get())
                                        .then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.empty()))
                                ));
    }


    @Bean
    public RouterFunction<ServerResponse> managePurchases(
            FindAllPurchases findAllPurchases,
            FindPurchaseById findPurchaseById,
            FindPurchasesByCxName findPurchasesByCxName,
            PerformPurchase performPurchase
    ) {
        return
                route(GET("/purchases"),
                        request -> ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(findAllPurchases.findAllPurchases(),
                                        Purchase.class)
                                )
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).build()))
                        .and(route(GET("/purchase"),
                                request -> ServerResponse
                                        .ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(
                                                findPurchaseById.findPurchaseById(request.queryParam("id").get()),
                                                Purchase.class))
                                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).build())))
                        .and(route(GET("/cx_purchase"),
                                request -> ServerResponse
                                        .ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(
                                                findPurchasesByCxName.findPurchasesByCxName(request.queryParam("name").get()),
                                                Purchase.class))
                                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
                        ))
                        .and(route(POST("/purchase"),
                                request -> ServerResponse
                                        .ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromPublisher(
                                                performPurchase.performPurchase(request.bodyToMono(PurchaseDTO.class)),
                                                PurchaseDTO.class))
                                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
                                ))


                ;
    }
}
